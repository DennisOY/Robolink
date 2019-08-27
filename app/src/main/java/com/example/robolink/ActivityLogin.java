package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

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
                // TODO: 2019/8/20 要更新账号密码的设定 
                if(account.equals("admin") && password.equals(("123"))){//设定一个管理员的账号密码，以后这里要更新

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
                }else{
                    Toast.makeText(ActivityLogin.this, "账号密码错误，请重新输入", Toast.LENGTH_SHORT).show();
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

    public void initView(){
        customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_login);
        accountEditText = (EditText) findViewById(R.id.edit_accout) ;
        passwordEditText = (EditText) findViewById(R.id.edit_password);

        rememberPasswordCheckBox = (CheckBox) findViewById(R.id.password_remembered);
        rememberLoginStatus = findViewById(R.id.status_remembered);

        btn_login = (Button) findViewById(R.id.btn_login);
        text_2register = findViewById(R.id.login2register);

        //设置标题栏内容、字体大小
        customTitleBar.setLeftDrawable(R.drawable.ic_out);
        customTitleBar.setLeftTextSize(18);
        customTitleBar.setLeftText(getResources().getString(R.string.back));

        btn_login.setEnabled(false);
        passwordEditText.setText("");
        accountEditText.setText("");

        preferences = getSharedPreferences("login_info", MODE_PRIVATE);
        editor = preferences.edit();
        //preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
}
