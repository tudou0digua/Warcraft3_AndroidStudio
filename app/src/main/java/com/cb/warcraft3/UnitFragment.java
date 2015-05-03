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
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UnitFragment extends Fragment {
    private Context context;
    private View rootView;
    private String dirName;
    private static int listPosition;
    private static String fileName;
    private String[][] UnitInfo;
    private String[][][] UnitSkillInfo;
    private TextView[] unitText=new TextView[22];
    private TextView[][] skillText=new TextView[7][3];
    private ImageView unitImage;
    private ImageView[] skillImage=new ImageView[7];
    private RelativeLayout[] relativeLayouts =new RelativeLayout[7];

    public UnitFragment() {

    }

    public static UnitFragment newInstance(int listPosition,String fileNameUnit,String dirName){
        UnitFragment unitFragment=new UnitFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("listPosition", listPosition);
        bundle.putString("fileNameUnit",fileNameUnit);
        bundle.putString("dirName", dirName);
        unitFragment.setArguments(bundle);
        return unitFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args=getArguments();
        if(args!=null){
            listPosition =args.getInt("listPosition");
            fileName=args.getString("fileNameUnit");
            dirName=args.getString("dirName");
        }

        context = getActivity();
        setJson(parseJson(fileName));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.unit,container,false);
        bindConpoment();
        setUnitView(listPosition);
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

    //解析Json数据，放入UnitInfo和UnitSkillInfo数组
    private void setJson(String str){
        try {
            JSONArray array = new JSONArray(str);
            int len = array.length();
            UnitInfo = new String[23][len];
            UnitSkillInfo = new String[len][][];
            //get unitInfo and unitSkillInfo
            for(int i = 0; i < len; i++){
                JSONObject object = array.getJSONObject(i);
                JSONArray skill = object.getJSONArray("skill");
                UnitSkillInfo[i] = new String[skill.length()][];
                //get unitSkillDetaill
                for(int j = 0; j < skill.length(); j++){
                    JSONObject skillObject = skill.getJSONObject(j);
                    UnitSkillInfo[i][j] = new String[4];
                    UnitSkillInfo[i][j][0] = skillObject.getString("skill");
                    UnitSkillInfo[i][j][1] = skillObject.getString("skilldescription");
                    UnitSkillInfo[i][j][2] = skillObject.getString("skilldetail");
                    UnitSkillInfo[i][j][3] = skillObject.getString("skillimage");
                }
                //get UnitDetail
                UnitInfo[0][i] = object.getString("level");
                UnitInfo[1][i] = object.getString("cost");
                UnitInfo[2][i] = object.getString("unitType");
                UnitInfo[3][i] = object.getString("attackType");
                UnitInfo[4][i] = object.getString("weaponType");
                UnitInfo[5][i] = object.getString("armonType");
                UnitInfo[6][i] = object.getString("armon");
                UnitInfo[7][i] = object.getString("groundAttack");
                UnitInfo[8][i] = object.getString("airAttack");
                UnitInfo[9][i] = object.getString("life");
                UnitInfo[10][i] = object.getString("lifeRecovery");
                UnitInfo[11][i] = object.getString("mana");
                UnitInfo[12][i] = object.getString("manaRecovery");
                UnitInfo[13][i] = object.getString("attackRange");
                UnitInfo[14][i] = object.getString("dayView");
                UnitInfo[15][i] = object.getString("nightView");
                UnitInfo[16][i] = object.getString("speed");
                UnitInfo[17][i] = object.getString("trainingTime");
                UnitInfo[18][i] = object.getString("trainingPlace");
                UnitInfo[19][i] = object.getString("requirement");
                UnitInfo[20][i] = object.getString("hotKey");
                UnitInfo[21][i] = object.getString("description");
                UnitInfo[22][i] = object.getString("imageName3");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //绑定各组件
    private void bindConpoment(){
        //bind attribute
        unitText[0] = (TextView) rootView.findViewById(R.id.textView_1);
        unitText[1] = (TextView) rootView.findViewById(R.id.textView_2);
        unitText[2] = (TextView) rootView.findViewById(R.id.textView_3);
        unitText[3] = (TextView) rootView.findViewById(R.id.textView_4);
        unitText[4] = (TextView) rootView.findViewById(R.id.textView_5);
        unitText[5] = (TextView) rootView.findViewById(R.id.textView_6);
        unitText[6] = (TextView) rootView.findViewById(R.id.textView_7);
        unitText[7] = (TextView) rootView.findViewById(R.id.textView_8);
        unitText[8] = (TextView) rootView.findViewById(R.id.textView_9);
        unitText[9] = (TextView) rootView.findViewById(R.id.textView_10);
        unitText[10] = (TextView) rootView.findViewById(R.id.textView_11);
        unitText[11] = (TextView) rootView.findViewById(R.id.textView_12);
        unitText[12] = (TextView) rootView.findViewById(R.id.textView_13);
        unitText[13] = (TextView) rootView.findViewById(R.id.textView_14);
        unitText[14] = (TextView) rootView.findViewById(R.id.textView_15);
        unitText[15] = (TextView) rootView.findViewById(R.id.textView_16);
        unitText[16] = (TextView) rootView.findViewById(R.id.textView_17);
        unitText[17] = (TextView) rootView.findViewById(R.id.textView_18);
        unitText[18] = (TextView) rootView.findViewById(R.id.textView_19);
        unitText[19] = (TextView) rootView.findViewById(R.id.textView_20);
        unitText[20] = (TextView) rootView.findViewById(R.id.textView_21);
        unitText[21] = (TextView) rootView.findViewById(R.id.textView_describe);
        //bind skillText
        skillText[0][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_1);
        skillText[0][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_1);
        skillText[0][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_1);
        skillText[1][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_2);
        skillText[1][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_2);
        skillText[1][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_2);
        skillText[2][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_3);
        skillText[2][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_3);
        skillText[2][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_3);
        skillText[3][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_4);
        skillText[3][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_4);
        skillText[3][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_4);
        skillText[4][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_5);
        skillText[4][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_5);
        skillText[4][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_5);
        skillText[5][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_6);
        skillText[5][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_6);
        skillText[5][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_6);
        skillText[6][0] = (TextView) rootView.findViewById(R.id.textView_skill_name_7);
        skillText[6][1] = (TextView) rootView.findViewById(R.id.textView_skill_desc_7);
        skillText[6][2] = (TextView) rootView.findViewById(R.id.textView_skill_detail_7);
        //bind unitImage
        unitImage = (ImageView) rootView.findViewById(R.id.imageView_unit);
        //bind skillImage
        skillImage[0] = (ImageView) rootView.findViewById(R.id.imageView_skill_1);
        skillImage[1] = (ImageView) rootView.findViewById(R.id.imageView_skill_2);
        skillImage[2] = (ImageView) rootView.findViewById(R.id.imageView_skill_3);
        skillImage[3] = (ImageView) rootView.findViewById(R.id.imageView_skill_4);
        skillImage[4] = (ImageView) rootView.findViewById(R.id.imageView_skill_5);
        skillImage[5] = (ImageView) rootView.findViewById(R.id.imageView_skill_6);
        skillImage[6] = (ImageView) rootView.findViewById(R.id.imageView_skill_7);
        //bind relativeLayout_skill
        relativeLayouts[0] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_1);
        relativeLayouts[1] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_2);
        relativeLayouts[2] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_3);
        relativeLayouts[3] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_4);
        relativeLayouts[4] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_5);
        relativeLayouts[5] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_6);
        relativeLayouts[6] = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_skill_7);
    }
    //加载unitFragment所有显示内容
    private void setUnitView(int listPosition){
        int skillNumber=UnitSkillInfo[listPosition].length;
        for(int i=0;i<22;i++){
            unitText[i].setText(UnitInfo[i][listPosition]);
        }
        unitImage.setImageBitmap(getImage(dirName+UnitInfo[22][listPosition]));
        //set skillView
        for(int i=0;i<7;i++){
            relativeLayouts[i].setVisibility(View.GONE);
        }
        for(int i=0;i<skillNumber;i++){
            relativeLayouts[i].setVisibility(View.VISIBLE);
            skillImage[i].setImageBitmap(getImage(dirName+UnitSkillInfo[listPosition][i][3]));
            for(int j=0;j<3;j++){
                skillText[i][j].setText(UnitSkillInfo[listPosition][i][j]);
                if(j==1){
                    setTextSize(skillText[i][j], UnitSkillInfo[listPosition][i][j].length());
                }
                if(j==2){
                    if(UnitSkillInfo[listPosition][i][j].length()<3){
                        skillText[i][j].setVisibility(View.GONE);
                    }
                }
            }
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
    //skill_describe根据字数，调整字体大小。
    private void setTextSize(TextView textview,int len){
        if(len<50) textview.setTextSize(13);
        else if(len<57) textview.setTextSize(12);
        else if(len<60) textview.setTextSize(11);
        else if(len<88) textview.setTextSize(10);
        else textview.setTextSize(9);
    }
}
