package com.example.instagramloginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread t1 = new Thread(){
            public void run(){
                try {

                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }catch(Exception r){

                }
                finally {
                    Intent i1 =new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i1);
                }
            }
        };t1.start();
    }
}