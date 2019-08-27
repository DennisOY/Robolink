package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityCustomerService extends ActivityBase {

    private CustomTitleBar customTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
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

    private void initView(){
        customTitleBar = findViewById(R.id.titlebar_customer_service);

    }
}
