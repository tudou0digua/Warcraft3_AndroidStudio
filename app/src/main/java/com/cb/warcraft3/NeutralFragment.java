package com.cb.warcraft3;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NeutralFragment extends Fragment {
    private Context context;
    private ExpandableListView expandableListView;
    private String groupArray[];
    private String childArray[][];
    private Bitmap image[][];
    private String imageName[][];
    private int PHYLE = 2;
    private String childImageName = "imageName";
    private String childName = "name";
    private String dirName = "Neutral/";
    private String fileNameHero = "json/war3neutralhero.json";
    private String fileNameUnit = "json/war3neutralunit.json";
    private String fileNameArchitect = "json/war3neutralarchitect.json";
    private String fileListName = "json/war3listname.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expandablelistview,container,false);
        expandableListView = (ExpandableListView)view.findViewById(R.id.expandableListView);
        //初始化expandableListView
        setExpandableListView();
        return view;
    }
    //初始化expandableListView，设置Adapter和OnChildClickListener
    private void setExpandableListView(){
        //从json和本地读取groupArray、childArray和Image的数据
        setGroupArray();
        setChildArray();
        setImage();

        expandableListView.setAdapter(new MyELVAdapter(context, groupArray, childArray, image));
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                showDetail(groupPosition,childPosition);
                return false;
            }
        });
    }

    //解析json数据
    private String parseJson(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    //得到groupArray，即expandablelisview父列表元素值
    private void setGroupArray(){
        try {
            JSONArray jsonArray = new JSONArray(parseJson(fileListName));
            JSONObject jsonObject = jsonArray.getJSONObject(PHYLE);
            groupArray = new String[jsonObject.length()];
            String[] string = new String[]{"hero", "unit", "architect"};
            for (int i = 0; i < jsonObject.length(); i++){
                groupArray[i] = jsonObject.getString(string[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //解析json，根据文件名和某一元素的名称，如：“name”，得到json文件中所有“name”，返回一个string[]
    private String[] getName(String string, String fileName){
        String data[];
        String error[] = {"error"};
        try {
            JSONArray jsonArray = new JSONArray(parseJson(fileName));
            data = new String [jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                data[i] = jsonObject.getString(string);
            }
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
            return error;
        }
    }
    //得到chilidArray，即expandablelisview子列表元素值
    private void setChildArray(){
        childArray = new String [groupArray.length][];
        String [] fileName = new String[]{fileNameHero,fileNameUnit,fileNameArchitect};
        for (int i = 0; i < groupArray.length; i++){
            childArray[i] = getName(childName, fileName[i]);
        }
    }
    //根据文件名称，加载本地图片，并返回该图片的Bitmap格式
    private Bitmap getImage(String fileName){
        Bitmap bitmap = null;
        AssetManager assetManager = context.getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    //得到Image，即expandableListView所有子元素的Bitmap格式的图片，存入Image[]
    private void setImage(){
        //解析json得到所有图片名称
        imageName = new String[groupArray.length][];
        String [] fileName = new String[]{fileNameHero,fileNameUnit,fileNameArchitect};
        for (int i =0; i < imageName.length; i++){
            imageName[i] = getName(childImageName,fileName[i]);
        }
        //取得本地所有图片，存入image
        image = new Bitmap[imageName.length][];
        for(int i = 0; i < imageName.length; i++){
            image[i] = new Bitmap[imageName[i].length];
            for(int j = 0; j < image[i].length; j++){
                String string = dirName + imageName[i][j];
                image[i][j] = getImage(string);
            }
        }
    }
    //显示英雄、单位和建筑的详细数据
    public void showDetail(int groupPosition, int childPosition){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment[] fragment = new Fragment[3];
        fragment[0] = HeroFragment.newInstance(childPosition,fileNameHero,dirName);
        fragment[1] = UnitFragment.newInstance(childPosition,fileNameUnit,dirName);
        fragment[2] = ArchitectFragment.newInstance(childPosition,fileNameArchitect,dirName);
        //设置fragment从左边滑进，右边滑出动画
        fragmentTransaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        fragmentTransaction.replace(R.id.frameLayout, fragment[groupPosition]);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        fragmentTransaction.hide(this);
        fragmentTransaction.commit();
    }
}
