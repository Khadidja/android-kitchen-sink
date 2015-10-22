package com.akhadidja.kitchensink.volley;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class VolleyJSONRequest {

    private static final long TIMEOUT = 30000;
    private static final String LOG_TAG = VolleyJSONRequest.class.getSimpleName();

    public static JSONObject response (RequestQueue requestQueue, String url){
        JSONObject response = null;
        try {
            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    requestFuture,
                    requestFuture);

            requestQueue.add(request);
            response = requestFuture.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.d(LOG_TAG, "Interruption", e);
        } catch (ExecutionException e) {
            Log.d(LOG_TAG, "Execution", e);
        } catch (TimeoutException e) {
            Log.d(LOG_TAG, "Timed out", e);
        }
        return response;
    }
}
