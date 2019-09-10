package com.fii.robolink.SupportFunctions;

import android.os.Bundle;

import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.Base.ActivityBase;
import com.fii.robolink.R;

public class ActivityAbout extends ActivityBase {

    private CustomTitleBar customTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        customTitleBar.setSplitMode(true);
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    private void initView(){
        customTitleBar = findViewById(R.id.titlebar_about);
    }
}
