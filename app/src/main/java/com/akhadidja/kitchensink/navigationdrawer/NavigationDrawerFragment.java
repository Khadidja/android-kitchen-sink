package com.akhadidja.kitchensink.navigationdrawer;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akhadidja.kitchensink.MainActivity;
import com.akhadidja.kitchensink.R;

public class NavigationDrawerFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mContainerView;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private final String USER_LEARNED_DRAWER_KEY = "user_learned_drawer";
    private final String FROM_SAVED_INSTANCE_STATE_KEY = "from_saved_instance_state";

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.parseBoolean(
                readFromSharedPreferences(getActivity(), USER_LEARNED_DRAWER_KEY, "false"));

        if(savedInstanceState != null){
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar, int fragmentId) {
        mContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), USER_LEARNED_DRAWER_KEY, mUserLearnedDrawer+"");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        if(!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(mContainerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceKey, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.SHARED_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceKey, preferenceValue);
        editor.apply();
    }

    public static String readFromSharedPreferences(Context context, String preferenceKey,
                                                   String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.SHARED_PREF,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceKey, defaultValue);
    }
}
