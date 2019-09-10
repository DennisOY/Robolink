package com.fii.robolink.SupportFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.R;

public class ActivityProfile extends AppCompatActivity {

    private CustomTitleBar customTitleBar;
    private CustomTitleBar profile_photo;
    private CustomTitleBar nickname;
    private CustomTitleBar profile_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        //设置成分开模式
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
        profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
/*        profile_photo.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });*/
        nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
/*        nickname.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });*/
        profile_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
/*        profile_email.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });*/
    }

    private void initView() {
        customTitleBar = findViewById(R.id.titlebar_profile);
        profile_photo = findViewById(R.id.profile_photo);
        nickname = findViewById(R.id.nickname);
        profile_email = findViewById(R.id.profile_email);
    }
}

