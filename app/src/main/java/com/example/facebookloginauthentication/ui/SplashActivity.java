package com.example.facebookloginauthentication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.facebookloginauthentication.R;
import com.facebook.AccessToken;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //facebook check login or not
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                Log.i("shbdbjashj", "onCreate1: "+isLoggedIn);

               if (isLoggedIn){
                   startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));
                   finish();
               }
               else {
                   startActivity(new Intent(SplashActivity.this,MainActivity.class));
                   finish();
               }

            }
        },3000);
    }
}