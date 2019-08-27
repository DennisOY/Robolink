package com.example.robolink;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {

    private Context mcontext;
    private Drawable drawable;
    private Drawable drawable_hollow;

    public CustomEditText(Context context) {
        super(context);
        mcontext = context;
        initView();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        initView();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext = context;
        initView();
    }

    private void initView(){
        drawable = mcontext.getResources().getDrawable(R.mipmap.ic_clear);
        drawable_hollow = mcontext.getResources().getDrawable(R.mipmap.ic_clear_hollow);
        setDrawable();
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setDrawable();
            }
        });
    }

    private void setDrawable(){
        if(this.length() < 1){
            this.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,drawable_hollow,null);
        }else {
            this.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,drawable,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawable != null && event.getAction() == MotionEvent.ACTION_UP){
            int X = (int) event.getRawX();
            int Y = (int) event.getRawY();
            Rect rect = new Rect();
            //获取视图，置入一个方块中
            getGlobalVisibleRect(rect);
            //设置视图的边框，作为响应的部分
            rect.left = rect.right - 125; //此处数字限制了左边距，数值越大，对用户响应的范围也就越大
            if (rect.contains(X,Y))
                setText("");
        }
        return super.onTouchEvent(event);
    }
}
