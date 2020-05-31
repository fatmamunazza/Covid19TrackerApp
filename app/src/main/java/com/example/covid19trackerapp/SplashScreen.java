package com.example.covid19trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView covid=findViewById(R.id.t1);
        TextView tracker=findViewById(R.id.t2);
        ImageView covidImage=findViewById(R.id.covid);
        ImageView sanitizer=findViewById(R.id.sanitizer);



        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        tracker.startAnimation(animation);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        covidImage.startAnimation(animation1);

        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        covid.startAnimation(animation2);

        Animation animation3= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_from_top);
        sanitizer.startAnimation(animation3);

        Handler handler=new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreen.this,MainScreen.class));
                        finish();
                    }
                },
                2000);

    }
}
