package com.example.robolink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.widget.ViewFlipper;

public class ActivityWelcome extends ActivityBase {

    //private SharedPreferences.Editor write;
    private SharedPreferences preferences;
    private boolean isLoginStatusRemembered;

    private VideoView videoView;

    private ViewFlipper viewFlipper;

    private Button btn_register;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        //已经在布局中设置了自动开始
        //viewFlipper.startFlipping();

        //记住登录状态，直接进入主界面
        isLoginStatusRemembered = preferences.getBoolean("remember_login", false);//记住后面是默认的
        if(isLoginStatusRemembered) {
            startActivity(new Intent(ActivityWelcome.this, ActivityMain.class));
            finish();
        }

        //write = getSharedPreferences("data", MODE_PRIVATE).edit();


/*        Button btn_clear = (Button) findViewById(R.id.btn_clear) ;
        Button btn_to_fragment = (Button) findViewById(R.id.btn_to_fragment);*/

//视频在这循环播放

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/vedio_start_big"));

        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityWelcome.this, ActivityRegister.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityWelcome.this, ActivityLogin.class));
            }
        });
/*        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write.clear();//清空用户登录的记录
                write.apply();
                startActivity(new Intent(ActivityWelcome.this, ActivitySplash.class));
            }
        });
        btn_to_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityWelcome.this, ActivityMain.class);
                intent.putExtra("小黑洞", 4);
                startActivity(intent);
            }
        });*/
    }

    private void initView(){

        preferences = getSharedPreferences("login_info",MODE_PRIVATE);
        isLoginStatusRemembered = false;

        btn_register = findViewById(R.id.btn_to_register);
        btn_login =  findViewById(R.id.btn_to_login);

        videoView = findViewById(R.id.start_video);

        viewFlipper = findViewById(R.id.viewflipper);
    }
}
