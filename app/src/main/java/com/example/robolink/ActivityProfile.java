package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityProfile extends AppCompatActivity {

    private CustomTitleBar customTitleBar;

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
    }

    private void initView() {
        customTitleBar = findViewById(R.id.titlebar_profile);
    }
}

