package com.akhadidja.kitchensink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.akhadidja.kitchensink.splashscreen.SplashScreenActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSplashScreenActivity(View view) {
        startActivity(new Intent(this, SplashScreenActivity.class));
    }
}
