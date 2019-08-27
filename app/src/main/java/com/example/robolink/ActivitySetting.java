package com.example.robolink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class ActivitySetting extends ActivityBase {

    private CustomTitleBar customTitleBar;
    private CustomTitleBar clean_Bar;
    private CustomTitleBar update_Bar;
    private CustomTitleBar language_Bar;

    private SharedPreferences preferences;
    private SharedPreferences preferences_login;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editor_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
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

        clean_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySetting.this);
                builder.setMessage("请确认清除，清除返回启动页");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clean_cache();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }

            @Override
            public void rightClick() {
            }
        });

        update_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
            }
            @Override
            public void rightClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySetting.this);
                builder.setMessage("当前已是最新版本");
                builder.setPositiveButton("默认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        language_Bar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                startActivity(new Intent(ActivitySetting.this, ActivityLanguage.class));
            }

            @Override
            public void rightClick() {
                startActivity(new Intent(ActivitySetting.this, ActivityLanguage.class));
            }
        });
    }

    private void clean_cache(){
        editor.clear();
        editor_login.clear();
        editor.commit();
        editor_login.commit();
        startActivity(new Intent(ActivitySetting.this, ActivitySplash.class));
    }

    private void initView(){
        customTitleBar = findViewById(R.id.titlebar_setting);
        clean_Bar = findViewById(R.id.clean_bar);
        update_Bar=findViewById(R.id.update_bar);
        language_Bar=findViewById(R.id.language_bar);

        preferences = getSharedPreferences("data",MODE_PRIVATE);
        preferences_login = getSharedPreferences("login_info", MODE_PRIVATE);
        editor = preferences.edit();
        editor_login = preferences_login.edit();
    }
}
