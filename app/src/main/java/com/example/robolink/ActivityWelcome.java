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

public class ActivityWelcome extends AppCompatActivity {

    private SharedPreferences.Editor write;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        write = getSharedPreferences("data", MODE_PRIVATE).edit();

        Button btn_register = (Button) findViewById(R.id.btn_to_register);
        Button btn_login = (Button) findViewById(R.id.btn_to_login);
        Button btn_clear = (Button) findViewById(R.id.btn_clear) ;
        Button btn_to_fragment = (Button) findViewById(R.id.btn_to_fragment);
        videoView = (VideoView) findViewById(R.id.start_video);
        videoView.setVideoURI(Uri.parse( "android.resource://" + getPackageName()+  "/raw/vedio_start_big"));

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
        btn_clear.setOnClickListener(new View.OnClickListener() {
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
        });
    }
}
