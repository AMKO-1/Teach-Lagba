// SplashActivity.java
package com.batcat.teacher_lagba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("is_logged_in", false);

        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
        handler.postDelayed(() -> {
            if (isLoggedIn){
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish(); // Close SplashActivity
            }

        }, 2000); // 500 milliseconds = 0.5 seconds
    }
}
