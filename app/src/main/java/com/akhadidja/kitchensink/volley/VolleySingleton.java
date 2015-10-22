package com.akhadidja.kitchensink.volley;

import com.akhadidja.kitchensink.KitchenSinkApplication;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(KitchenSinkApplication.getAppContext());
    }

    public static VolleySingleton getInstance(){
        if(sInstance == null) {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }
    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
