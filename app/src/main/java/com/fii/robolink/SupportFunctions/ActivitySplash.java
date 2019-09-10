package com.fii.robolink.SupportFunctions;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.fii.robolink.Base.ActivityBase;
import com.fii.robolink.R;
import com.fii.robolink.Utils.LocaleUtils;

import java.util.ArrayList;
import java.util.List;

public class ActivitySplash extends ActivityBase implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArrayList<View> views = new ArrayList<>();
    private boolean isFirstTime;

    private SharedPreferences.Editor write;
    private SharedPreferences read;
    private SharedPreferences read_locale;

    private int userPreflocale;

    private Button btn_enter;

    private LinearLayout linearLayout;
    private ImageView[] dotViews;

/*    private ImageView imageView;
    private ProgressBar progressBar;
    private Handler handler = new Handler();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btn_enter =  findViewById(R.id.btn_enter);
/*        imageView =  findViewById(R.id.start_screen);
        progressBar = findViewById(R.id.start_screen_progress_bar);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    imageView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },2000);*/
        read_locale = getSharedPreferences("LOCALE_FILE", MODE_PRIVATE);
        userPreflocale = read_locale.getInt("LOCALE_KEY", 0);
        LocaleUtils.updateLocale(getBaseContext(),userPreflocale);
        write = getSharedPreferences("data", MODE_PRIVATE).edit();
        read = getSharedPreferences("data", MODE_PRIVATE);
        isFirstTime = read.getBoolean("newbee", true);
        if (isFirstTime) {
            initViews();
            initDots();
            viewPager.setAdapter(new ViewPagerAdatper(views));//将ViewPager和自定义的Adapter关联起来
            viewPager.addOnPageChangeListener(this);
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
    private void initViews() {
        viewPager = findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater().from(this);
        linearLayout = findViewById(R.id.dot);

        views.add(inflater.inflate(R.layout.startpic1, null));
        views.add(inflater.inflate(R.layout.startpic2, null));
        views.add(inflater.inflate(R.layout.startpic3, null));
        views.add(inflater.inflate(R.layout.startpic4, null));

    }

    //显示圆点，在Java代码中自定义View的办法
    private void initDots() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dotViews = new ImageView[4];
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setPadding(30, 0, 30, 0);
            imageView.setImageResource(R.drawable.bottom_dot);
            dotViews[i] = imageView;
            linearLayout.addView(imageView);
        }
        //进入页面的设为选图案
        dotViews[0].setImageResource(R.drawable.bottom_full_dot);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        //循环设定空点图案，并把当前位置设为实点
        for(int i= 0; i< 4; i++){
            dotViews[i].setImageResource(R.drawable.bottom_dot);
        }
        dotViews[position].setImageResource(R.drawable.bottom_full_dot);

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
