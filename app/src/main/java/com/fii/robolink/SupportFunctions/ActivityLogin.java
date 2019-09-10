package com.fii.robolink.SupportFunctions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fii.robolink.Base.ActivityBase;
import com.fii.robolink.Utils.HttpUtil;
import com.fii.robolink.CustomView.CustomTitleBar;
import com.fii.robolink.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityLogin extends ActivityBase {

    private CustomTitleBar customTitleBar;

    private EditText accountEditText;
    private String account;

    private EditText passwordEditText;
    private String password;

    private Button btn_login;
    private TextView text_2register;

    private CheckBox rememberPasswordCheckBox;
    private CheckBox rememberLoginStatus;
    private boolean isPasswordRemembered;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //设置成分开模式
        customTitleBar.setSplitMode(true);
        //自定义标题栏左侧view，即返回键的监听事件
        //使用了自定义标题栏类中的接口，这样就可以自定义响应了
        customTitleBar.setOnViewClick(new CustomTitleBar.onViewClick() {
            @Override
            public void leftClick() {
                //startActivity(new Intent(ActivityLogin.this, ActivityWelcome.class));
                //相当于后退键功能，直接结束活动
                finish();
            }
            @Override
            public void rightClick() {
            }
        });


        //监听输入栏
        accountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(TextUtils.isEmpty(accountEditText.getText()) || TextUtils.isEmpty((passwordEditText.getText()))){
                    btn_login.setEnabled(false);
                }else{
                    btn_login.setEnabled(true);//在这里设定按钮可用，只有两个输入栏都不为空的时候,有多少个输入栏就要设定多少次，好麻烦呀
                    //btn_login.setBackgroundColor(0xff2196f3);//代码设置颜色的N种方法，我都不会
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
                //隐藏密码
                //passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(accountEditText.getText()) || TextUtils.isEmpty((passwordEditText.getText()))){
                    btn_login.setEnabled(false);
                }else{
                    btn_login.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        //检查是否保存了密码,如果是则将文件中值自动填写上去
        isPasswordRemembered = preferences.getBoolean("remember_password", false);//后面参数是无返回值时的默认值
        if(isPasswordRemembered){
            account = preferences.getString("account","");
            password = preferences.getString("password","");
            accountEditText.setText(account);
            passwordEditText.setText(password);
            rememberPasswordCheckBox.setChecked(true);
        }

        //监听按钮，同时检查checkbox状态
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = accountEditText.getText().toString();
                password = passwordEditText.getText().toString();

                RequestBody requestBody = new FormBody.Builder()
                        .add("userName",account)
                        .add("password", password)
                        .build();
                HttpUtil.postOkHttpRequest("http://192.168.1.232:18088/httpServiceInter/checkLogin",requestBody,new okhttp3.Callback(){
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseData = response.body().string();
                        parseJSONWithJSONObject(responseData);
                    }
                });
                //登录后门
                if(account.equals("admin") && password.equals(("123"))){
                    if(rememberPasswordCheckBox.isChecked()){
                        editor.putBoolean("remember_password", true);//做一个标签文件，如果勾选了记住密码并登陆成功就会存储登录信息
                        editor.putString("account", account);
                        editor.putString("password", password);
                    }else{
                        editor.clear();
                    }
                    if(rememberLoginStatus.isChecked()){
                        editor.putBoolean("remember_login",true);
                    }else{
                        editor.putBoolean("remember_login", false);
                    }
                    editor.commit();//一定要commit才有效哦，或者apply()
                    startActivity(new Intent(ActivityLogin.this, ActivityMain.class));
                    finish();
                }
            }
        });

        text_2register.setClickable(true);
        text_2register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));
            }
        });
    }

    //解析JSON文件
    private void parseJSONWithJSONObject(String jsondata){
        String msg;
        try {
            JSONObject jsonObject = new JSONObject(jsondata);
            result = jsonObject.getString("result");
            msg = jsonObject.getString("message");
            getOutcome(result, msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //使用异步通信机制从线程中获取数值
    private void getOutcome(final String result, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(ActivityLogin.this, msg, Toast.LENGTH_LONG).show();
                    if (result.equals("yes")) {
                        if (rememberPasswordCheckBox.isChecked()) {
                            editor.putBoolean("remember_password", true);//做一个标签文件，如果勾选了记住密码并登陆成功就会存储登录信息
                            editor.putString("account", account);
                            editor.putString("password", password);
                        } else {
                            editor.clear();
                        }
                        if (rememberLoginStatus.isChecked()) {
                            editor.putBoolean("remember_login", true);
                        } else {
                            editor.putBoolean("remember_login", false);
                        }
                        editor.commit();//一定要commit才有效哦，或者apply()
                        startActivity(new Intent(ActivityLogin.this, ActivityMain.class));
                        finish();
                    }else{
                        editor.clear();
                        editor.apply();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initView(){
        customTitleBar = findViewById(R.id.titlebar_login);
        accountEditText = findViewById(R.id.edit_accout) ;
        passwordEditText = findViewById(R.id.edit_password);

        rememberPasswordCheckBox = findViewById(R.id.password_remembered);
        rememberLoginStatus = findViewById(R.id.status_remembered);

        btn_login =  findViewById(R.id.btn_login);
        btn_login.setEnabled(false);
        text_2register = findViewById(R.id.login2register);

        //设置标题栏内容、字体大小
        customTitleBar.setLeftDrawable(R.drawable.ic_out);
        customTitleBar.setLeftTextSize(18);
        customTitleBar.setLeftText(getResources().getString(R.string.back));

        passwordEditText.setText("");
        accountEditText.setText("");

        preferences = getSharedPreferences("login_info", MODE_PRIVATE);
        editor = preferences.edit();
        //preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
}
