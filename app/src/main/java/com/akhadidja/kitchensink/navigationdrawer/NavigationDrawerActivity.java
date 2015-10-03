package com.akhadidja.kitchensink.navigationdrawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.akhadidja.kitchensink.R;

public class NavigationDrawerActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        textView = (TextView) findViewById(R.id.navigation_drawer_textView);
        toolbar = (Toolbar) findViewById(R.id.navigation_drawer_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);
        drawerFragment.setUp(drawerLayout, toolbar, R.id.navigation_drawer_fragment);
    }

    public void onDrawerOptionClick(String option){
        textView.setText(option);
    }
}
