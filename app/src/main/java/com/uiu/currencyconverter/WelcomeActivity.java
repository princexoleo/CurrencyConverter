package com.uiu.currencyconverter;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.support.graphics.drawable.AnimationUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends AppCompatActivity {

    Timer timer;
    TextView textView;
    Animation frombottom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombttom);  //set animation to thetextView
        textView=findViewById(R.id.text_id);
        textView.setAnimation(frombottom);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);   //this activity will wait for 5 sec
    }
}
