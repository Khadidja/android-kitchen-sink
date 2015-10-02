package com.akhadidja.kitchensink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.akhadidja.kitchensink.navigationdrawer.NavigationDrawerActivity;
import com.akhadidja.kitchensink.pulltorefresh.PullToRefreshActivity;
import com.akhadidja.kitchensink.reusablelayout.ReusableLayoutActivity;
import com.akhadidja.kitchensink.splashscreen.SplashScreenActivity;

public class MainActivity extends AppCompatActivity {

    public final static String SHARED_PREF = "Kitchen_Prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSplashScreenActivity(View view) {
        startActivity(new Intent(this, SplashScreenActivity.class));
    }

    public void showReusableLayoutActivity(View view) {
        startActivity(new Intent(this, ReusableLayoutActivity.class));
    }

    public void showPullToRefreshActivity(View view) {
        startActivity(new Intent(this, PullToRefreshActivity.class));
    }

    public void showNavigationDrawerActivity(View view) {
        startActivity(new Intent(this, NavigationDrawerActivity.class));
    }
}
