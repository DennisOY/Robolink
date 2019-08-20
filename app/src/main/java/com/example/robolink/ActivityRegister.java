package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ActivityRegister extends AppCompatActivity {

    private CustomTitleBar customTitleBar;
    private EditText authenticationcodeEditText;
    private Button btn_get_authentication_code;
    private EditText passwordEditText;
    private EditText nameEditText;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        //自定义监听事件
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                startActivity(new Intent(ActivityRegister.this, ActivityWelcome.class));
            }
            @Override
            public void rightClick() {
            }
        });

        //监听输入栏
        authenticationcodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText()) ){
                    btn_register.setEnabled(false);
                    btn_register.setBackgroundColor(0xffc7c7c7);
                }else{
                    btn_register.setEnabled(true);//在这里设定按钮可用，只有三个输入栏都不为空的时候可用
                    btn_register.setBackgroundColor(0xff2196f3);//代码设置颜色
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //监听第二个输入栏
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText())){
                    btn_register.setEnabled(false);
                    btn_register.setBackgroundColor(0xffc7c7c7);
                }else{
                    btn_register.setEnabled(true);
                    btn_register.setBackgroundColor(0xff2196f3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //隐藏密码
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        //监听第三个输入栏
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText())){
                    btn_register.setEnabled(false);
                    btn_register.setBackgroundColor(0xffc7c7c7);
                }else{
                    btn_register.setEnabled(true);
                    btn_register.setBackgroundColor(0xff2196f3);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void initView(){

        customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_register);
        authenticationcodeEditText = (EditText) findViewById(R.id.edit_code);
        btn_get_authentication_code = (Button) findViewById(R.id.send_authentication_code);
        passwordEditText = (EditText) findViewById(R.id.edit_regi_password);
        nameEditText = (EditText) findViewById(R.id.edit_regi_name);
        btn_register = findViewById(R.id.btn_register);

        customTitleBar.setLeftDrawable(R.mipmap.ic_back);
        customTitleBar.setLeftTextSize(18);
        customTitleBar.setLeftText(getResources().getString(R.string.back));

        btn_register.setEnabled(false);
        btn_register.setBackgroundColor(0xffc7c7c7);
    }
}
