package com.akhadidja.kitchensink.pulltorefresh;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhadidja.kitchensink.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PullToRefreshFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView dateTimeTextView;

    public PullToRefreshFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pull_to_refresh, container, false);
        dateTimeTextView = (TextView) rootView.findViewById(R.id.date_time_textView);

        dateTimeTextView.setText(getDateAndTime());
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dateTimeTextView.setText(getDateAndTime());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        return rootView;
    }

    private String getDateAndTime() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        dateFormatter.setLenient(false);
        Date today = new Date();
        return dateFormatter.format(today);
    }
}
