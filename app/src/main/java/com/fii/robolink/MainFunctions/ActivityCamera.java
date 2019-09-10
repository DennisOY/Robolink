package com.fii.robolink.MainFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.R;

public class ActivityCamera extends AppCompatActivity {

    CustomTitleBar customTitleBar;
    TextView textView;
    Intent intent;

    // TODO: 2019/9/2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        customTitleBar = findViewById(R.id.titlebar_shot);
        textView = findViewById(R.id.camera_tag);
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

        intent = getIntent();
        int mode = intent.getIntExtra("mode", 0);
        switch (mode) {
            case 0:
                customTitleBar.setTitle("孤岛模式");
                break;
            case 1:
                customTitleBar.setTitle("巡逻模式");
                break;
            case 2:
                customTitleBar.setTitle("参观模式");
                break;
            case 3:
                customTitleBar.setTitle("顺序模式");
                break;
            case 4:
                customTitleBar.setTitle("分布模式");
                break;
            default:
                break;
        }
    }
}
