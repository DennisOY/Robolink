package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        profile_photo.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });
        nickname.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });
        profile_email.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });
    }

    private void initView() {
        customTitleBar = findViewById(R.id.titlebar_profile);
        profile_photo = findViewById(R.id.profile_photo);
        nickname = findViewById(R.id.nickname);
        profile_email = findViewById(R.id.profile_email);
    }
}

