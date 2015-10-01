package com.akhadidja.kitchensink.pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.akhadidja.kitchensink.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PullToRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView dateTimeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        dateTimeTextView = (TextView) findViewById(R.id.date_time_textView);

        dateTimeTextView.setText(getDateAndTime());
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(PullToRefreshActivity.this,
                        "Updating Date and Time in 3 sec", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dateTimeTextView.setText(getDateAndTime());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private String getDateAndTime (){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        dateFormatter.setLenient(false);
        Date today = new Date();
        return dateFormatter.format(today);
    }
}
