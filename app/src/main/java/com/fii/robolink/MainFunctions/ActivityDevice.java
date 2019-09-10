package com.fii.robolink.MainFunctions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.R;

public class ActivityDevice extends AppCompatActivity {

    CustomTitleBar customTitleBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        customTitleBar = findViewById(R.id.titlebar_device);
        imageView = findViewById(R.id.device_3d_mode);
        Glide.with(this).load(R.raw.defect_detector).into(imageView);
        //设置成分开模式
        customTitleBar.setSplitMode(true);
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                finish();
//                startActivity(new Intent(ActivityDevice.this, ActivityMain.class));
            }

            @Override
            public void rightClick() {

            }
        });

        int device_no;
        int device_type;
        Intent intent = getIntent();
        device_no = intent.getIntExtra("Device", -1);
        device_type = intent.getIntExtra("Type", -1);
        if (device_type == 0) {
            switch (device_no) {
                case 0:
                    customTitleBar.setTitle(getResources().getString(R.string.CNC1_5_Axis));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 1:
                    customTitleBar.setTitle(getResources().getString(R.string.FOX_BOT_1));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 2:
                    customTitleBar.setTitle(getResources().getString(R.string.CNC2_FANUC));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 3:
                    customTitleBar.setTitle(getResources().getString(R.string.FOX_BOT_2));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 4:
                    customTitleBar.setTitle(getResources().getString(R.string.CNC2_FANUC));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 5:
                    customTitleBar.setTitle(getResources().getString(R.string.Googoltech_1));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 6:
                    customTitleBar.setTitle(getResources().getString(R.string.AGV1));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 7:
                    customTitleBar.setTitle(getResources().getString(R.string.Googoltech_2));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                    default:break;
            }
        } else if(device_type == 1){
            switch (device_no) {
                case 0:
                    customTitleBar.setTitle(getResources().getString(R.string.Feeder));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 1:
                    customTitleBar.setTitle(getResources().getString(R.string.FOX_BOT_3));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 2:
                    customTitleBar.setTitle(getResources().getString(R.string.ATM));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 3:
                    customTitleBar.setTitle(getResources().getString(R.string.Googoltech_3));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                case 4:
                    customTitleBar.setTitle(getResources().getString(R.string.AGV2));
//                    Glide.with(this).load(R.raw.defect_detector).into(imageView);
                    break;
                default:break;
            }
        }else{
            Toast.makeText(ActivityDevice.this, "无此设备", Toast.LENGTH_SHORT).show();
        }

    }
}
