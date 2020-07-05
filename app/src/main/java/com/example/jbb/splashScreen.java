package com.example.jbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jbb.sign.login;

public class splashScreen extends AppCompatActivity {

    private static  int SPLASH_TIMER = 5000;

    ImageView logo;
    TextView by;

    Animation sideAnim, botAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        by = findViewById(R.id.by);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        sideAnim.setDuration(1500);
        botAnim.setDuration(1600);

        logo.setAnimation(sideAnim);
        by.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(splashScreen.this, login.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIMER);

    }
}
