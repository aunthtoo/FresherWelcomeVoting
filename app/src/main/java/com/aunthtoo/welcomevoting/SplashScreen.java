package com.aunthtoo.welcomevoting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Aunt Htoo on 1/9/2017.
 */

public class SplashScreen extends AppCompatActivity {

    TextView t1,t2;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        t1=(TextView)findViewById(R.id.txt1);
        t2=(TextView)findViewById(R.id.txt2);
        img=(ImageView)findViewById(R.id.imgg);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,Main.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        YoYo.with(Techniques.SlideInDown)
                .duration(2000)
                .playOn(t1);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(img);

        YoYo.with(Techniques.SlideInUp)
                .duration(2000)
                .playOn(t2);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}
