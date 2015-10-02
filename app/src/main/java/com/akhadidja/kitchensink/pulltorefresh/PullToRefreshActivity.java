package com.akhadidja.kitchensink.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.akhadidja.kitchensink.R;

public class PullToRefreshActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        toolbar = (Toolbar) findViewById(R.id.pull_to_refresh_toolbar);
        setSupportActionBar(toolbar);
    }
}
