package com.fii.robolink.SupportFunctions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import android.content.res.Resources;
import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.R;
import com.fii.robolink.Utils.LocaleUtils;

import java.util.Locale;

public class ActivityLanguage extends AppCompatActivity {


    private CustomTitleBar customTitleBar;
    private CustomTitleBar chinese_simplified;
    private CustomTitleBar chinese_traditional;
    private CustomTitleBar english;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        Locale locale = new Locale("en");
        Configuration config = this.getResources().getConfiguration();
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        config.locale = Locale.ENGLISH;
        this.getResources().updateConfiguration(config, metrics);*/
        setContentView(R.layout.activity_language);

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

        chinese_simplified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleUtils.updateLocale(ActivityLanguage.this,0);
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLanguage.this);
                builder.setMessage("已切换至简体中文");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO: 2019/9/10  考虑杀死
                        finish();
                        startActivity(new Intent(ActivityLanguage.this,ActivityMain.class));
                    }
                });
                builder.show();
            }
        });

        chinese_traditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleUtils.updateLocale(getBaseContext(),1);
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLanguage.this);
                builder.setMessage("已切换至繁体中文");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(new Intent(ActivityLanguage.this,ActivityMain.class));
                    }
                });
                builder.show();
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleUtils.updateLocale(getBaseContext(),2);
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLanguage.this);
                builder.setMessage("已切换至英语");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        startActivity(new Intent(ActivityLanguage.this,ActivityMain.class));
                    }
                });
                builder.show();
            }
        });
    }

    private void initView(){
        customTitleBar = findViewById(R.id.titlebar_language);
        chinese_simplified = findViewById(R.id.chinese_simplified);
        chinese_traditional=findViewById(R.id.chinese_traditional);
        english=findViewById(R.id.english);
    }
}
