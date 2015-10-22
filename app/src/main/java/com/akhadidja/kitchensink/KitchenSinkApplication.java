package com.akhadidja.kitchensink;

import android.app.Application;
import android.content.Context;

public class KitchenSinkApplication extends Application {

    private static KitchenSinkApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
