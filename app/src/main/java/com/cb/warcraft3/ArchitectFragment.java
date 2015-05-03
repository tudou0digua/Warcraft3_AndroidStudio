package com.cb.warcraft3;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArchitectFragment extends Fragment {
    private Context context;
    private View rootView;
    private String dirName;
    private static int listPosition;
    private static String fileName;
    private String[] architect;
    private ImageView architectImage;

    public ArchitectFragment() {
        // Required empty public constructor
    }

    public static ArchitectFragment newInstance(int listPosition,String fileNameArchit,String dirName){
        ArchitectFragment architectFragment=new ArchitectFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("listPosition", listPosition);
        bundle.putString("fileNameArchit",fileNameArchit);
        bundle.putString("dirName", dirName);
        architectFragment.setArguments(bundle);
        return architectFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args=getArguments();
        if(args!=null){
            listPosition =args.getInt("listPosition");
            fileName=args.getString("fileNameArchit");
            dirName=args.getString("dirName");
        }

        context = getActivity();
        setJson(parseJson(fileName));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.architect,container,false);
        setArchitectView();
        return rootView;
    }

    //读取fileName中所有json数据，返回String
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

    //解析Json数据，放入architect数组
    private void setJson(String str){
        try{
            JSONArray array=new JSONArray(str);
            int len=array.length();
            architect=new String[len];
            for(int i=0;i<len;i++){
                JSONObject object=array.getJSONObject(i);
                architect[i]=object.getString("imageName3");
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    //加载ArchitectFragment的视图的所有数据
    private void setArchitectView(){
        architectImage = (ImageView) rootView.findViewById(R.id.imageView_architect);
        architectImage.setImageBitmap(getImage(dirName + architect[listPosition]));
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


}
