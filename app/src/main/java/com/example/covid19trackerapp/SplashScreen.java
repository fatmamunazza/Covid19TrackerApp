package com.example.covid19trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView covid=findViewById(R.id.t1);
        TextView tracker=findViewById(R.id.t2);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        tracker.startAnimation(animation);

        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        covid.startAnimation(animation2);

        Handler handler=new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreen.this,MainScreen.class));
                    }
                },
                1500);

    }
}
