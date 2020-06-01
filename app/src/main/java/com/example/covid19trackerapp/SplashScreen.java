package com.example.covid19trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView covid=findViewById(R.id.t1);
        TextView tracker=findViewById(R.id.t2);
        ImageView covidImage=findViewById(R.id.covid);
        ImageView sanitizer=findViewById(R.id.sanitizer);
        progressBar=findViewById(R.id.horizontalProgressBar);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        tracker.startAnimation(animation);

        Animation animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        covidImage.startAnimation(animation1);

        Animation animation2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        covid.startAnimation(animation2);

        Animation animation3= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_from_top);
        sanitizer.startAnimation(animation3);


        // timer for seekbar
        final int oneTime = 3 * 1000; // 3 secs in milli seconds

        /** CountDownTimer starts with 3 sec and every onTick is 0.1 second */
        new CountDownTimer(oneTime, 100) {
            public void onTick(long millisUntilFinished) {
                //forward progress
                long finishedSeconds = oneTime - millisUntilFinished;
                int total = (int) (((float)finishedSeconds / (float)oneTime) * 100.0);

                progressBar.setProgress(total);

//                //backward progress
//                int total = (int) (((float) millisUntilFinished / (float) oneMin) * 100.0);
//                progressBar.setProgress(total);

            }

            public void onFinish() {
                // DO something when 1 minute is up
                startActivity(new Intent(SplashScreen.this,MainScreen.class));
                finish();
            }
        }.start();

    }
}
