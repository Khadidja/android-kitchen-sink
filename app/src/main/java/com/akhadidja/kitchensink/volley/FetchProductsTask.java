package com.akhadidja.kitchensink.volley;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FetchProductsTask extends AsyncTask<Void, Void, ArrayList<Product>> {

    private static final String LOG_TAG = FetchProductsTask.class.getSimpleName();
    private String mUrl;
    private ProductsLoaderListener mListener;
    private RequestQueue mRequestQueue;

    public FetchProductsTask (String url, ProductsLoaderListener listener){
        mUrl = url;
        mListener = listener;
        VolleySingleton mVolleySingleton = VolleySingleton.getInstance();
        mRequestQueue = mVolleySingleton.getRequestQueue();
    }
    @Override
    protected ArrayList<Product> doInBackground(Void... params) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Product.class, new ProductDeserializer());
        Gson gson = gsonBuilder.create();

        try {
            JSONObject response = VolleyJSONRequest.response(mRequestQueue, mUrl);
            JSONArray productsJsonArray = response.getJSONArray("products");
            Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
            productArrayList = new Gson().fromJson(productsJsonArray.toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        if(mListener != null){
            mListener.onProductsLoaded(products);
        }
    }
}
