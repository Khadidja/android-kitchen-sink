package com.akhadidja.kitchensink.volley;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FetchProductsTask extends AsyncTask<Void, Void, ArrayList<Product>> {

    private static final String LOG_TAG = FetchProductsTask.class.getSimpleName();
    private String mUrl;
    private ProductsLoaderListener mProductsLoaderListener;
    private RequestQueue mRequestQueue;
    private OnVolleyErrorListener mOnVolleyErrorListener;

    public FetchProductsTask (OnVolleyErrorListener volleyErrorListener, String url,
                              ProductsLoaderListener productsLoaderListener){
        mUrl = url;
        mProductsLoaderListener = productsLoaderListener;
        mOnVolleyErrorListener = volleyErrorListener;
        VolleySingleton mVolleySingleton = VolleySingleton.getInstance();
        mRequestQueue = mVolleySingleton.getRequestQueue();
    }
    @Override
    protected ArrayList<Product> doInBackground(Void... params) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        try {
            JSONObject response =
                    VolleyJSONRequest.response(mOnVolleyErrorListener, mRequestQueue, mUrl);
            if(response != null){
                JSONArray productsJsonArray = response.getJSONArray("products");
                Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
                productArrayList = new Gson().fromJson(productsJsonArray.toString(), listType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        if(mProductsLoaderListener != null){
            mProductsLoaderListener.onProductsLoaded(products);
        }
    }
}
