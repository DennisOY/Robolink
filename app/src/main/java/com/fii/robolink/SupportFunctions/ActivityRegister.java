package com.fii.robolink.SupportFunctions;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

public class ActivityRegister extends ActivityBase {

    private CustomTitleBar customTitleBar;
    private EditText authenticationcodeEditText;
    private Button btn_get_authentication_code;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText mailEditText;
    private Button btn_register;

    private CountDownTimer timer;

    String veriCode;
    String uuid;
    String input_veriCode;
    String input_name;
    String input_psw;
    String input_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        //设置成分开模式
        customTitleBar.setSplitMode(true);
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
                timer = new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btn_get_authentication_code.setEnabled(false);
                        btn_get_authentication_code.setText((millisUntilFinished + 1000) / 1000 + "秒后重新发送");//加1000是为了不显示0秒，最后一个显示的是1秒
                    }

                    @Override
                    public void onFinish() {
                        btn_get_authentication_code.setEnabled(true);
                        btn_get_authentication_code.setText("获取验证码");
                        /*Intent intent = new Intent(ActivityRegister.this,ActivityMain.class);
                        intent.putExtra("直接去设备",3);//标签是“直接去设备”，后面活动会对应标签使用不同获取数据类型的方法来获取值，若无对应标签，则设为默认
                        startActivity(intent);*/
                    }
                }.start();
                //sendRequestWithOkHttp();
                HttpUtil.sendOkHttpRequest("http://192.168.1.232:18088/httpServiceInter/getVerCodeNew", new okhttp3.Callback(){
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
            }
        });

        //监听验证码输入栏
        authenticationcodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(TextUtils.isEmpty(mailEditText.getText()) || TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText()) ){
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
        authenticationcodeEditText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        //监听密码输入栏
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(mailEditText.getText()) || TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText())){
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

        //监听姓名输入栏
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(mailEditText.getText()) || TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText())){
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

        //监听邮箱输入栏
        mailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(mailEditText.getText()) || TextUtils.isEmpty(authenticationcodeEditText.getText()) || TextUtils.isEmpty(passwordEditText.getText()) || TextUtils.isEmpty(nameEditText.getText())){
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

        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_veriCode = authenticationcodeEditText.getText().toString().trim();
                input_name = nameEditText.getText().toString().trim();
                input_psw = passwordEditText.getText().toString().trim();
                input_mail = mailEditText.getText().toString().trim();
                Log.d("ActivityRegister", "In register Click, the uuid is " + uuid);
                Log.d("ActivityRegister", "In register Click, the verification code is " + veriCode);
                RequestBody requestBody = new FormBody.Builder()
                        .add("userName", input_name)
                        .add("password", input_psw)
                        .add("email", input_mail)
                        .add("inputCode", veriCode)
                        .add("uuid", uuid)
                        .build();
                HttpUtil.postOkHttpRequest("http://192.168.1.232:18088/httpServiceInter/register", requestBody, new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseData = response.body().string();
                        parseJSONWithJSONObject_registerinfo(responseData);
                    }
                });
            }
        });
    }

/*    //以OkHttp的方式向服务器“192.168.1.232”发送验证码请求
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.1.232:18088/httpServiceInter/getVerCodeNew")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }*/

    //解析服务器返回的验证码JSON文件
    private void parseJSONWithJSONObject(String jsondata) {

        try {
            JSONObject jsonObject = new JSONObject(jsondata);
            veriCode = jsonObject.getString("verCode");
            uuid = jsonObject.getString("uuid");
            Log.d("ActivitiRegister", "In thread: " + veriCode);
            Log.d("ActivitiRegister", "In thread: " + uuid);
            getOutcome(veriCode, uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析服务器返回的注册JSON文件
    private void parseJSONWithJSONObject_registerinfo(String jsondata) {
        String result;
        String msg;
        try {
            JSONObject jsonObject = new JSONObject(jsondata);
            result = jsonObject.getString("result");
            msg = jsonObject.getString("message");
            Log.d("ActivitiRegister", "result is: " + result);
            getRegiOutcome(result,msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用异步通信机制从线程中获取数值
    private void getOutcome(final String code, final String id){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(ActivityRegister.this, "您的验证码是" + code + "， 请十秒后再试", Toast.LENGTH_LONG).show();
                    // TODO: 2019/9/4 去掉自动填写 
                    authenticationcodeEditText.setText(code);//测试阶段，获取验证码后自动填写
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    //使用异步通信机制从线程中获取注册响应
    private void getRegiOutcome(final String result, final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(ActivityRegister.this, msg, Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void initView() {

        customTitleBar = findViewById(R.id.titlebar_register);
        authenticationcodeEditText = findViewById(R.id.edit_code);
        btn_get_authentication_code = findViewById(R.id.send_authentication_code);
        passwordEditText = findViewById(R.id.edit_regi_password);
        nameEditText = findViewById(R.id.edit_regi_name);
        mailEditText = findViewById(R.id.edit_regi_mail);
        btn_register = findViewById(R.id.btn_register);

        customTitleBar.setLeftDrawable(R.drawable.ic_out);
        customTitleBar.setLeftTextSize(18);
        customTitleBar.setLeftText(getResources().getString(R.string.back));

        btn_register.setEnabled(false);
        //btn_register.setBackgroundColor(0xffc7c7c7);
    }
}
