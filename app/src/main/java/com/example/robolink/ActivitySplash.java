package com.example.robolink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArrayList<View> views = new ArrayList<>();
    private boolean isFirstTime;

    private SharedPreferences.Editor write;
    private SharedPreferences read;

    private Button btn_enter;
    //private ImageView imageView;
    //private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btn_enter = (Button) findViewById(R.id.btn_enter);

        /*imageView = (ImageView) findViewById(R.id.start_screen);
        progressBar = (ProgressBar) findViewById(R.id.start_screen_progress_bar);

         new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.run();
       imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.GONE);
            }
        },2000);

        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        },2000);*/


        write = getSharedPreferences("data", MODE_PRIVATE).edit();
        read = getSharedPreferences("data", MODE_PRIVATE);
        isFirstTime = read.getBoolean("newbee", true);
        if(isFirstTime) {
            initViews();
            viewPager.setAdapter(new ViewPagerAdatper(views));//将ViewPager和自定义的Adapter关联起来
            viewPager.setOnPageChangeListener(this);
        }else {
            startActivity(new Intent(ActivitySplash.this, ActivityWelcome.class));
            finish();
        }
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivitySplash.this, ActivityWelcome.class));
                write.putBoolean("newbee", false);//设置文件中newbee标签对应值为false，意味着记录用户不是第一次登录
                write.apply();
                finish();
            }
        });
    }

    //加载要显示的View
    private void initViews(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater().from(this);

        views.add(inflater.inflate(R.layout.startpic1,null));
        views.add(inflater.inflate(R.layout.startpic2,null));
        views.add(inflater.inflate(R.layout.startpic3,null));
        views.add(inflater.inflate(R.layout.startpic4,null));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        if(position == views.size() - 1){
            btn_enter.setVisibility(View.VISIBLE);//设置按钮不可见
        }else {
            btn_enter.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //内部类帮助将view加载到PagerAdapter容器中
    class ViewPagerAdatper extends PagerAdapter{

        private List<View> mViewList;

        public ViewPagerAdatper(List<View> mViewList){
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList.get(position));
        }
    }

}
