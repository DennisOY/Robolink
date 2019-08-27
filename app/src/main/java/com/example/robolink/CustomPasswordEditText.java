package com.example.robolink;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomPasswordEditText extends AppCompatEditText {

    private Context mcontext;
    private Drawable vis;
    private Drawable invis;
    private boolean visibility;

    public CustomPasswordEditText(Context context) {
        super(context);
        mcontext = context;
        initView();
    }

    public CustomPasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        initView();
    }

    public CustomPasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
        initView();
    }

    private void initView(){
        vis = mcontext.getResources().getDrawable(R.mipmap.ic_visible);
        invis = mcontext.getResources().getDrawable(R.mipmap.ic_invisible);
        visibility = false;
        setInVis();
    }

    private void setVis() {
        //this.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        this.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//显示密码
        visibility = true;
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, invis, null);//显示出不可见以供切换
    }

    private void setInVis(){
        //this.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        this.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码
        visibility = false;
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, vis, null);//显示出可见以供切换
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {



        Rect rect = new Rect();
        //获取视图，置入一个方块中
        getGlobalVisibleRect(rect);
        //设置视图的边框，作为响应的部分
        rect.left = rect.right - 125; //此处数字限制了左边距，数值越大，对用户响应的范围也就越大

        if (visibility == false && event.getAction() == MotionEvent.ACTION_UP){
            //获取点击事件的XY值
            int X = (int) event.getRawX();
            int Y = (int) event.getRawY();
            if (rect.contains(X,Y))
                setVis();
        }else if(visibility == true && event.getAction() == MotionEvent.ACTION_UP){
            //获取点击事件的XY值
            int X = (int) event.getRawX();
            int Y = (int) event.getRawY();
            if (rect.contains(X,Y))
                setInVis();
        }
        return super.onTouchEvent(event);
    }
}
