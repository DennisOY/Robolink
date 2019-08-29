package com.example.robolink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ActivityLanguage extends AppCompatActivity {


    private CustomTitleBar customTitleBar;
    private CustomTitleBar chinese_simplified;
    private CustomTitleBar chinese_traditional;
    private CustomTitleBar english;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

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

        chinese_simplified.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLanguage.this);
                builder.setMessage("已切换至简体中文");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
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

        chinese_traditional.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLanguage.this);
                builder.setMessage("已切换至繁体中文");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
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

        english.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLanguage.this);
                builder.setMessage("已切换至繁体中文");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
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
    }

    private void initView(){
        customTitleBar = findViewById(R.id.titlebar_language);
        chinese_simplified = findViewById(R.id.chinese_simplified);
        chinese_traditional=findViewById(R.id.chinese_traditional);
        english=findViewById(R.id.english);
    }
}
