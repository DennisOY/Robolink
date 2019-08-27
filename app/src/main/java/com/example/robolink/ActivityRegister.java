package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ActivityRegister extends ActivityBase {

    private CustomTitleBar customTitleBar;
    private EditText authenticationcodeEditText;
    private Button btn_get_authentication_code;
    private EditText passwordEditText;
    private EditText nameEditText;
    private Button btn_register;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        //自定义监听事件
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                //startActivity(new Intent(ActivityRegister.this, ActivityWelcome.class));
                finish();
            }
            @Override
            public void rightClick() {
            }
        });

        //获取验证码
        btn_get_authentication_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer = new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btn_get_authentication_code.setEnabled(false);
                        btn_get_authentication_code.setText((millisUntilFinished + 1000) / 1000 + "秒后重新发送");//加1000是为了不显示0秒，最后一个显示的是1秒
                    }

                    @Override
                    public void onFinish() {
                        btn_get_authentication_code.setEnabled(true);
                        btn_get_authentication_code.setText("获取验证码");
                        Intent intent = new Intent(ActivityRegister.this,ActivityMain.class);
                        intent.putExtra("直接去设备",3);//标签是“直接去设备”，后面活动会对应标签使用不同获取数据类型的方法来获取值，若无对应标签，则设为默认
                        startActivity(intent);
                    }
                }.start();
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
                    //btn_register.setBackgroundColor(0xffc7c7c7);
                }else{
                    btn_register.setEnabled(true);//在这里设定按钮可用，只有三个输入栏都不为空的时候可用
                    //btn_register.setBackgroundColor(0xff2196f3);//代码设置颜色
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
                    //btn_register.setBackgroundColor(0xffc7c7c7);
                }else{
                    btn_register.setEnabled(true);
                    //btn_register.setBackgroundColor(0xff2196f3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //隐藏密码,已于自定义EditText中实现可切换，故删去
                //passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                    //btn_register.setBackgroundColor(0xffc7c7c7);
                }else{
                    btn_register.setEnabled(true);
                    //btn_register.setBackgroundColor(0xff2196f3);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void initView(){

        customTitleBar = findViewById(R.id.titlebar_register);
        authenticationcodeEditText =  findViewById(R.id.edit_code);
        btn_get_authentication_code = findViewById(R.id.send_authentication_code);
        passwordEditText = findViewById(R.id.edit_regi_password);
        nameEditText = findViewById(R.id.edit_regi_name);
        btn_register = findViewById(R.id.btn_register);

        customTitleBar.setLeftDrawable(R.drawable.ic_out);
        customTitleBar.setLeftTextSize(18);
        customTitleBar.setLeftText(getResources().getString(R.string.back));

        btn_register.setEnabled(false);
        //btn_register.setBackgroundColor(0xffc7c7c7);
    }
}
