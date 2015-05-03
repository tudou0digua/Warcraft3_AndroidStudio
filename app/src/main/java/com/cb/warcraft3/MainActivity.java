package com.cb.warcraft3;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private ActionBar.TabListener myTabListener;
    public final static int TAB_INDEX_HUMAN = 0;
    public final static int TAB_INDEX_ELV = 1;
    public final static int TAB_INDEX_NEUTRAL = 2;
    public final static int TAB_INDEX_ORCISH = 3;
    public final static int TAB_INDEX_UNDEAD = 4;
    public final static int TAB_COUNT=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.pager);
        addTab();
        setViewPager();
    }

    private void addTab(){
        ActionBar actionBar = getActionBar();
        setMyTabListener();
        //
        actionBar.addTab(actionBar.newTab()
                        .setText("人族")
                        .setTabListener(myTabListener)
        );
        actionBar.addTab(actionBar.newTab()
                        .setText("精灵")
                        .setTabListener(myTabListener)
        );
        actionBar.addTab(actionBar.newTab()
                        .setText("中立")
                        .setTabListener(myTabListener)
        );
        actionBar.addTab(actionBar.newTab()
                        .setText("兽族")
                        .setTabListener(myTabListener)
        );
        actionBar.addTab(actionBar.newTab()
                        .setText("不死")
                        .setTabListener(myTabListener)
        );
        //
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    public void setMyTabListener() {
        myTabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentById(R.id.frameLayout);
                if(fragment != null){
                    fragmentManager.popBackStack();
                    fragmentTransaction.remove(fragment).commit();

                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
    }

    //
    private void setViewPager(){
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new MyPagerListener());
        viewPager.setCurrentItem(TAB_INDEX_HUMAN);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case TAB_INDEX_HUMAN:
                    return new HumanFragment();
                case TAB_INDEX_ELV:
                    return new ELVFragment();
                case TAB_INDEX_NEUTRAL:
                    return new NeutralFragment();
                case TAB_INDEX_ORCISH:
                    return new OrcishFragment();
                case TAB_INDEX_UNDEAD:
                    return new UndeadFragment();
                default:
                    return new UndeadFragment();
            }
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }

    public class MyPagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            getActionBar().setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    //监听返回键，若当前页面为列表页面，则程序隐藏到后台（并不关闭），若为详细信息页面，则保持原来返回键功能不变。
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if(fragment == null){
            moveTaskToBack(false);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_MENU){
            final Dialog dialog = new Dialog(this,R.style.MyDialogStyle);
            dialog.setContentView(R.layout.dialog_exit);
            Window window = dialog.getWindow();
            //设置Dialog的宽度为当前屏幕宽度80%
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            layoutParams.width = (int)(displayMetrics.widthPixels*0.8);
            window.setAttributes(layoutParams);
            dialog.show();
            //监听取消按钮
            window.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            //监听退出按钮
            window.findViewById(R.id.button_exit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(0);
                }
            });
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
