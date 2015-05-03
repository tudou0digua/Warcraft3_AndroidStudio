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
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class HeroFragment extends Fragment {
    private Context context;
    private View view;
    private String dirName;
    private static int listPosition;
    private static String fileNameHero;
    public String[][] heroInfo;
    private ImageView heroimage,skill1image,skill2image,skill3image,skill4image;
    private TextView name,property,speed,hotkey,attackInterval,str,dex,intelli,level,attack,armon,life,mana,cost;
    private TextView description,skillname1,skillname2,skillname3,skillname4,skill1desc,skill2desc,skill3desc,skill4desc;
    private TextView skill1detail,skill2detail,skill3detail,skill4detail;
    private SeekBar seekBar;

    public static HeroFragment newInstance(int listPosition, String fileNameHero, String dirName){
        HeroFragment heroFragment = new HeroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("listPosition",listPosition);
        bundle.putString("fileNameHero",fileNameHero);
        bundle.putString("dirName",dirName);
        heroFragment.setArguments(bundle);
        return heroFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            listPosition = args.getInt("listPosition");
            fileNameHero = args.getString("fileNameHero");
            dirName = args.getString("dirName");
        }

        context = getActivity();
        setJson(parseJson(fileNameHero));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hero,container,false);
        bindComponent();
        setHeroView();
        return view;
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

    //解析Json数据，放入heroInfo数组
    private void setJson(String str){
        try{
            JSONArray array = new JSONArray(str);
            int len = array.length();
            heroInfo = new String[76][len];
            //get heroInfo
            for(int i=0;i<len;i++){
                JSONObject object=array.getJSONObject(i);
                heroInfo[0][i]=object.getString("name");
                heroInfo[1][i]=object.getString("description");
                heroInfo[2][i]=object.getString("cost");
                heroInfo[3][i]=object.getString("attackType");
                heroInfo[4][i]=object.getString("weaponType");
                heroInfo[5][i]=object.getString("ArmonType");
                heroInfo[6][i]=object.getString("attackInterval");
                heroInfo[7][i]=object.getString("attackRange");
                heroInfo[8][i]=object.getString("property");
                heroInfo[9][i]=object.getString("str");
                heroInfo[10][i]=object.getString("dex");
                heroInfo[11][i]=object.getString("int");
                heroInfo[12][i]=object.getString("lifeRecovery");
                heroInfo[13][i]=object.getString("manaRecovery");
                heroInfo[14][i]=object.getString("dayView");
                heroInfo[15][i]=object.getString("nightView");
                heroInfo[16][i]=object.getString("speed");
                heroInfo[17][i]=object.getString("traningTime");
                heroInfo[18][i]=object.getString("hotKey");
                heroInfo[19][i]=object.getString("attack1");
                heroInfo[20][i]=object.getString("attack2");
                heroInfo[21][i]=object.getString("attack3");
                heroInfo[22][i]=object.getString("attack4");
                heroInfo[23][i]=object.getString("attack5");
                heroInfo[24][i]=object.getString("attack6");
                heroInfo[25][i]=object.getString("attack7");
                heroInfo[26][i]=object.getString("attack8");
                heroInfo[27][i]=object.getString("attack9");
                heroInfo[28][i]=object.getString("attack10");
                heroInfo[29][i]=object.getString("armon1");
                heroInfo[30][i]=object.getString("armon2");
                heroInfo[31][i]=object.getString("armon3");
                heroInfo[32][i]=object.getString("armon4");
                heroInfo[33][i]=object.getString("armon5");
                heroInfo[34][i]=object.getString("armon6");
                heroInfo[35][i]=object.getString("armon7");
                heroInfo[36][i]=object.getString("armon8");
                heroInfo[37][i]=object.getString("armon9");
                heroInfo[38][i]=object.getString("armon10");
                heroInfo[39][i]=object.getString("life1");
                heroInfo[40][i]=object.getString("life2");
                heroInfo[41][i]=object.getString("life3");
                heroInfo[42][i]=object.getString("life4");
                heroInfo[43][i]=object.getString("life5");
                heroInfo[44][i]=object.getString("life6");
                heroInfo[45][i]=object.getString("life7");
                heroInfo[46][i]=object.getString("life8");
                heroInfo[47][i]=object.getString("life9");
                heroInfo[48][i]=object.getString("life10");
                heroInfo[49][i]=object.getString("mana1");
                heroInfo[50][i]=object.getString("mana2");
                heroInfo[51][i]=object.getString("mana3");
                heroInfo[52][i]=object.getString("mana4");
                heroInfo[53][i]=object.getString("mana5");
                heroInfo[54][i]=object.getString("mana6");
                heroInfo[55][i]=object.getString("mana7");
                heroInfo[56][i]=object.getString("mana8");
                heroInfo[57][i]=object.getString("mana9");
                heroInfo[58][i]=object.getString("mana10");
                heroInfo[59][i]=object.getString("skill1");
                heroInfo[60][i]=object.getString("skill2");
                heroInfo[61][i]=object.getString("skill3");
                heroInfo[62][i]=object.getString("skill4");
                heroInfo[63][i]=object.getString("skill1image");
                heroInfo[64][i]=object.getString("skill2image");
                heroInfo[65][i]=object.getString("skill3image");
                heroInfo[66][i]=object.getString("skill4image");
                heroInfo[67][i]=object.getString("skill1description");
                heroInfo[68][i]=object.getString("skill2description");
                heroInfo[69][i]=object.getString("skill3description");
                heroInfo[70][i]=object.getString("skill4description");
                heroInfo[71][i]=object.getString("skill1detail");
                heroInfo[72][i]=object.getString("skill2detail");
                heroInfo[73][i]=object.getString("skill3detail");
                heroInfo[74][i]=object.getString("skill4detail");
                heroInfo[75][i]=object.getString("imageName3");
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    //绑定各组件
    private void bindComponent(){
        seekBar = (SeekBar) view.findViewById(R.id.seekBar_level);

        heroimage = (ImageView)view.findViewById(R.id.imageView_hero);
        skill1image = (ImageView)view.findViewById(R.id.imageView_skill_1);
        skill2image = (ImageView)view.findViewById(R.id.imageView_skill_2);
        skill3image = (ImageView)view.findViewById(R.id.imageView_skill_3);
        skill4image = (ImageView)view.findViewById(R.id.imageView_skill_4);

        name = (TextView)view.findViewById(R.id.textView_1);
        property = (TextView)view.findViewById(R.id.textView_3);
        speed = (TextView)view.findViewById(R.id.textView_7);
        hotkey = (TextView)view.findViewById(R.id.textView_11);
        attackInterval = (TextView)view.findViewById(R.id.textView_15);
        cost = (TextView)view.findViewById(R.id.textView_5);
        str = (TextView)view.findViewById(R.id.textView_9);
        dex = (TextView)view.findViewById(R.id.textView_13);
        intelli = (TextView)view.findViewById(R.id.textView_17);

        level = (TextView)view.findViewById(R.id.textView_23);
        attack = (TextView)view.findViewById(R.id.textView_24);
        armon = (TextView)view.findViewById(R.id.textView_25);
        life = (TextView)view.findViewById(R.id.textView_26);
        mana = (TextView)view.findViewById(R.id.textView_27);

        description = (TextView)view.findViewById(R.id.textView_describe);
        skillname1 = (TextView)view.findViewById(R.id.textView_skill_name_1);
        skillname2 = (TextView)view.findViewById(R.id.textView_skill_name_2);
        skillname3 = (TextView)view.findViewById(R.id.textView_skill_name_3);
        skillname4 = (TextView)view.findViewById(R.id.textView_skill_name_4);
        skill1desc = (TextView)view.findViewById(R.id.textView_skill_desc_1);
        skill2desc = (TextView)view.findViewById(R.id.textView_skill_desc_2);
        skill3desc = (TextView)view.findViewById(R.id.textView_skill_desc_3);
        skill4desc = (TextView)view.findViewById(R.id.textView_skill_desc_4);
        skill1detail = (TextView)view.findViewById(R.id.textView_skill_detail_1);
        skill2detail = (TextView)view.findViewById(R.id.textView_skill_detail_2);
        skill3detail = (TextView)view.findViewById(R.id.textView_skill_detail_3);
        skill4detail = (TextView)view.findViewById(R.id.textView_skill_detail_4);
    }
    //读取各组件的值
    private void setHeroView(){
        setSeekBar();

        cost.setText(heroInfo[2][listPosition]);
        name.setText(heroInfo[0][listPosition]);
        property.setText(heroInfo[8][listPosition]);
        speed.setText(heroInfo[16][listPosition]);
        hotkey.setText(heroInfo[18][listPosition]);
        attackInterval.setText(heroInfo[6][listPosition]);
        str.setText(heroInfo[9][listPosition]);
        dex.setText(heroInfo[10][listPosition]);
        intelli.setText(heroInfo[11][listPosition]);
        description.setText(heroInfo[1][listPosition]);
        skillname1.setText(heroInfo[59][listPosition]);
        skillname2.setText(heroInfo[60][listPosition]);
        skillname3.setText(heroInfo[61][listPosition]);
        skillname4.setText(heroInfo[62][listPosition]);
        skill1desc.setText(heroInfo[67][listPosition]);
        setTextSize(skill1desc, heroInfo[67][listPosition].length());
        skill2desc.setText(heroInfo[68][listPosition]);
        setTextSize(skill2desc, heroInfo[68][listPosition].length());
        skill3desc.setText(heroInfo[69][listPosition]);
        setTextSize(skill3desc, heroInfo[69][listPosition].length());
        skill4desc.setText(heroInfo[70][listPosition]);
        setTextSize(skill4desc, heroInfo[70][listPosition].length());
        skill1detail.setText(heroInfo[71][listPosition]);
        skill2detail.setText(heroInfo[72][listPosition]);
        skill3detail.setText(heroInfo[73][listPosition]);
        skill4detail.setText(heroInfo[74][listPosition]);

        String stringDir;
        stringDir=dirName+heroInfo[75][listPosition];
        heroimage.setImageBitmap(getImage(stringDir));
        stringDir=dirName+heroInfo[63][listPosition];
        skill1image.setImageBitmap(getImage(stringDir));
        stringDir=dirName+heroInfo[64][listPosition];
        skill2image.setImageBitmap(getImage(stringDir));
        stringDir=dirName+heroInfo[65][listPosition];
        skill3image.setImageBitmap(getImage(stringDir));
        stringDir=dirName+heroInfo[66][listPosition];
        skill4image.setImageBitmap(getImage(stringDir));
    }

    //设置seekBar的操作
    private void setSeekBar(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0) progress=1;
                String pro = "" + progress + "";
                int pp = progress;
                level.setText(pro);
                attack.setText(heroInfo[18+pp][listPosition]);
                armon.setText(heroInfo[28+pp][listPosition]);
                life.setText(heroInfo[38+pp][listPosition]);
                mana.setText(heroInfo[48+pp][listPosition]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
