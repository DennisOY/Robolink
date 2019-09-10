package com.fii.robolink.SupportFunctions;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.Base.ActivityBase;
import com.fii.robolink.R;

public class ActivityHelp extends ActivityBase {

    private CustomTitleBar customTitleBar;
    private ImageView expand_1;
    private ImageView colapse_1;
    private ImageView expand_2;
    private ImageView colapse_2;
    private ImageView expand_3;
    private ImageView colapse_3;
    private ImageView expand_4;
    private ImageView colapse_4;
    private ImageView expand_5;
    private ImageView colapse_5;

    private TextView content_1;
    private TextView content_2;
    private TextView content_3;
    private TextView content_4;
    private TextView content_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
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

        expand_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_1.setVisibility(View.INVISIBLE);
                colapse_1.setVisibility(View.VISIBLE);
                content_1.setVisibility(View.VISIBLE);
            }
        });

        colapse_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_1.setVisibility(View.VISIBLE);
                colapse_1.setVisibility(View.INVISIBLE);
                content_1.setVisibility(View.GONE);
            }
        });

        expand_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_4.setVisibility(View.INVISIBLE);
                colapse_4.setVisibility(View.VISIBLE);
                content_4.setVisibility(View.VISIBLE);
            }
        });

        colapse_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_4.setVisibility(View.VISIBLE);
                colapse_4.setVisibility(View.INVISIBLE);
                content_4.setVisibility(View.GONE);
            }
        });

        expand_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_2.setVisibility(View.INVISIBLE);
                colapse_2.setVisibility(View.VISIBLE);
                content_2.setVisibility(View.VISIBLE);
            }
        });

        colapse_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_2.setVisibility(View.VISIBLE);
                colapse_2.setVisibility(View.INVISIBLE);
                content_2.setVisibility(View.GONE);
            }
        });

        expand_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_3.setVisibility(View.INVISIBLE);
                colapse_3.setVisibility(View.VISIBLE);
                content_3.setVisibility(View.VISIBLE);
            }
        });

        colapse_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_3.setVisibility(View.VISIBLE);
                colapse_3.setVisibility(View.INVISIBLE);
                content_3.setVisibility(View.GONE);
            }
        });

        expand_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_5.setVisibility(View.INVISIBLE);
                colapse_5.setVisibility(View.VISIBLE);
                content_5.setVisibility(View.VISIBLE);
            }
        });

        colapse_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_5.setVisibility(View.VISIBLE);
                colapse_5.setVisibility(View.INVISIBLE);
                content_5.setVisibility(View.GONE);
            }
        });
    }

    private void initView(){
        customTitleBar = findViewById(R.id.titlebar_help);
        expand_1 = findViewById(R.id.help_expand_1);
        colapse_1 = findViewById(R.id.help_colapse_1);
        expand_2 = findViewById(R.id.help_expand_2);
        colapse_2 = findViewById(R.id.help_colapse_2);
        expand_3 = findViewById(R.id.help_expand_3);
        colapse_3 = findViewById(R.id.help_colapse_3);
        expand_4 = findViewById(R.id.help_expand_4);
        colapse_4 = findViewById(R.id.help_colapse_4);
        expand_5 = findViewById(R.id.help_expand_5);
        colapse_5 = findViewById(R.id.help_colapse_5);

        content_1 = findViewById(R.id.content_1);
        content_2 = findViewById(R.id.content_2);
        content_3 = findViewById(R.id.content_3);
        content_4 = findViewById(R.id.content_4);
        content_5 = findViewById(R.id.content_5);
    }
}
