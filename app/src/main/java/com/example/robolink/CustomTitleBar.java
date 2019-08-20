package com.example.robolink;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomTitleBar extends RelativeLayout{

    private ImageView bar_left_view, bar_right_view;
    private TextView bar_left_text, bar_right_text, my_bar_title;
    private LinearLayout bar_left, bar_right;
    private onViewClick click;

    public CustomTitleBar(Context context){
        this(context, null);
    }
    public CustomTitleBar(Context context, AttributeSet attributeSet){
        this(context, attributeSet, 0);
    }
    public CustomTitleBar(Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_titlebar, this);
        initView();
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.TitlebarView, defStyleAttr, 0);

        int count = array.getIndexCount();
        for (int i = 0; i < count; i++){
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.TitlebarView_leftTextColor:
                    bar_left_text.setTextColor(array.getColor(attr, Color.BLACK));
                    break;
                case R.styleable.TitlebarView_leftDrawble:
                    bar_left_view.setImageResource(array.getResourceId(attr, 0));
                    break;
                case R.styleable.TitlebarView_leftText:
                    bar_left_text.setText(array.getString(attr));
                    break;
                case R.styleable.TitlebarView_centerTextColor:
                    my_bar_title.setTextColor(array.getColor(attr, Color.BLACK));
                    break;
                case R.styleable.TitlebarView_centerTitle:
                    my_bar_title.setText(array.getString(attr));
                    break;
                case R.styleable.TitlebarView_rightDrawable:
                    bar_right_view.setImageResource(array.getResourceId(attr, 0));
                    break;
                case R.styleable.TitlebarView_rightText:
                    bar_right_text.setText(array.getString(attr));
                    break;
                case R.styleable.TitlebarView_rightTextColor:
                    bar_right_text.setTextColor(array.getColor(attr, Color.BLACK));
                    break;
            }
        }
        array.recycle();
        bar_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                click.leftClick();

            }
        });
        bar_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                click.rightClick();
            }
        });
    }

    private void initView(){
        bar_left_text = (TextView) findViewById(R.id.bar_left_text);
        bar_right_text = (TextView) findViewById(R.id.bar_right_text);
        my_bar_title = (TextView) findViewById(R.id.bar_title);
        bar_left_view = (ImageView) findViewById(R.id.bar_left_view);
        bar_right_view = (ImageView) findViewById(R.id.bar_right_view);

        bar_left = (LinearLayout) findViewById(R.id.bar_left);
        bar_right = (LinearLayout) findViewById(R.id.bar_right);
    }

    public void setOnViewClick(onViewClick click) {
        this.click = click;
    }

    //设置标题
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            my_bar_title.setText(title);
        }
    }

    //设置左标题
    public void setLeftText(String title) {
        if (!TextUtils.isEmpty(title)) {
            bar_left_text.setText(title);
        }
    }

    //设置右标题
    public void setRightText(String title) {
        if (!TextUtils.isEmpty(title)) {
            bar_right_text.setText(title);
        }
    }

    //设置标题大小
    public void setTitleSize(int size) {
        if (my_bar_title != null) {
            my_bar_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    //设置左标题大小
    public void setLeftTextSize(int size) {
        if (bar_left_text != null) {
            bar_left_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    //设置右标题大小
    public void setRightTextSize(int size) {
        if (bar_right_text != null) {
            bar_right_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    //设置左图标
    public void setLeftDrawable(int res) {
        if (bar_left_view != null) {
            bar_left_view.setImageResource(res);
        }
    }

    //设置右图标
    public void setRightDrawable(int res) {
        if (bar_right_view != null) {
            bar_right_view.setImageResource(res);
        }
    }

    public interface onViewClick {
        void leftClick();

        void rightClick();
    }
}
