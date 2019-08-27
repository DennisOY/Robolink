package com.example.robolink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMain extends ActivityBase implements ViewPager.OnPageChangeListener {

    private CustomTitleBar customTitleBar;
    private BottomNavigationView navigationView;
    private CustomViewPager viewPager;

    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentCamera fragmentCamera = new FragmentCamera();
    private FragmentAudioControl fragmentAudioControl = new FragmentAudioControl();
    private FragmentDevices fragmentDevices = new FragmentDevices();
    private FragmentSetting fragmentSetting = new FragmentSetting();
    private Intent intent;


    private F2F f2F;
    //实现Fragment跳转用的接口
    public interface F2F{
        void toF(ViewPager viewPager);
    }
    public void setF2F(F2F f2F){
        this.f2F = f2F;
    }
    public void forSkip(){
        if(f2F != null ){
            f2F.toF(viewPager);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (CustomViewPager) findViewById(R.id.viewpager_main);
        viewPager.addOnPageChangeListener(this);

        navigationView = (BottomNavigationView) findViewById(R.id.navi);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemselectedListener);
        navigationView.setItemIconTintList(null);
        navigationView.getMenu().findItem(R.id.navigation_home).setIcon(R.mipmap.ic_home);

        customTitleBar = (CustomTitleBar) findViewById(R.id.bar_view) ;
        customTitleBar.setTitle(getResources().getString(R.string.home));
        customTitleBar.setTitleSize(18);//设置标题栏文字大小

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return fragmentMain;
                    case 1:
                        return fragmentCamera;
                    case 2:
                        return fragmentAudioControl;
                    case 3:
                        return fragmentDevices;
                    case 4:
                        return fragmentSetting;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 5;
            }
        });

        //设置骇客的传送门，跳过登录直接抵达一个碎片
        intent = getIntent();
        int portal = intent.getIntExtra("直接去设备", 0);
        viewPager.setCurrentItem(portal);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            resetToDefaultIcon();
            switch(item.getItemId()){
                case R.id.navigation_home:
                    item.setIcon(R.mipmap.ic_home);
                    customTitleBar.setTitle(getResources().getString(R.string.home));
                    viewPager.setCurrentItem(item.getOrder());
                    return true;
                case R.id.navigation_audiocontrol:
                    item.setIcon(R.mipmap.ic_voice);
                    customTitleBar.setTitle(getResources().getString(R.string.voice));
                    viewPager.setCurrentItem(item.getOrder());
                    return true;
                case R.id.navigation_camera:
                    item.setIcon(R.mipmap.ic_screenshot);
                    customTitleBar.setTitle(getResources().getString(R.string.shot));
                    viewPager.setCurrentItem(item.getOrder());
                    return true;
                case R.id.navigation_setting:
                    item.setIcon(R.mipmap.ic_me);
                    customTitleBar.setTitle(getResources().getString(R.string.me));
                    viewPager.setCurrentItem(item.getOrder());
                    return true;
                case R.id.navigation_devices:
                    item.setIcon(R.mipmap.ic_device_data);
                    customTitleBar.setTitle(getResources().getString(R.string.device_data));
                    viewPager.setCurrentItem(item.getOrder());
                    return true;
            }
            return false;
        }
    };
//复原所有icon
    public void resetToDefaultIcon(){
        /*MenuItem home = navigationView.getMenu().findItem(R.id.navigation_home);
        home.setIcon(R.drawable.ic_home);*/
        navigationView.getMenu().findItem(R.id.navigation_home).setIcon(R.mipmap.ic_home_none);
        MenuItem audiocontrol = navigationView.getMenu().findItem(R.id.navigation_audiocontrol);
        audiocontrol.setIcon(R.mipmap.ic_voice_none);
        MenuItem camera = navigationView.getMenu().findItem(R.id.navigation_camera);
        camera.setIcon(R.mipmap.ic_screenshot_none);
        MenuItem setting = navigationView.getMenu().findItem(R.id.navigation_setting);
        setting.setIcon(R.mipmap.ic_me_none);
        MenuItem devices = navigationView.getMenu().findItem(R.id.navigation_devices);
        devices.setIcon(R.mipmap.ic_device_data_none);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //viewpager滑动时改变底部图标
    @Override
    public void onPageSelected(int position) {
        navigationView.getMenu().getItem(position).setChecked(true);//页面选中时选项卡高亮
        if (position == 0) {
            resetToDefaultIcon();
            navigationView.getMenu().findItem(R.id.navigation_home).setIcon(R.mipmap.ic_home);
            customTitleBar.setTitle(getResources().getString(R.string.home));
            //Toast.makeText(ActivityMain.this, "maya!", Toast.LENGTH_SHORT).show();
        }else if (position == 1) {
            resetToDefaultIcon();
            customTitleBar.setTitle(getResources().getString(R.string.shot));
            navigationView.getMenu().findItem(R.id.navigation_camera).setIcon(R.mipmap.ic_screenshot);
        }else if (position == 2) {
            resetToDefaultIcon();
            customTitleBar.setTitle(getResources().getString(R.string.voice));
            navigationView.getMenu().findItem(R.id.navigation_audiocontrol).setIcon(R.mipmap.ic_voice);
        }else if (position == 3) {
            resetToDefaultIcon();
            customTitleBar.setTitle(getResources().getString(R.string.device_data));
            navigationView.getMenu().findItem(R.id.navigation_devices).setIcon(R.mipmap.ic_device_data);
        }else if (position == 4) {
            resetToDefaultIcon();
            customTitleBar.setTitle(getResources().getString(R.string.me));
            navigationView.getMenu().findItem(R.id.navigation_setting).setIcon(R.mipmap.ic_me);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
