package com.akhadidja.kitchensink.splashscreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akhadidja.kitchensink.R;
import com.akhadidja.kitchensink.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_TIMER = 3000;
    private final String YQL_EUROPE_COUNTRIES_BASE =
            "https://query.yahooapis.com/v1/public/yql?" +
                    "q=select%20*%20from%20geo.countries%20where%20place%3D%22Europe%22" +
                    "&format=json&diagnostics=true&callback=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new FetchAndroidHiveGameStatsTask().execute();
            }
        }, SPLASH_TIMER);
    }

    public class FetchAndroidHiveGameStatsTask extends AsyncTask<Void, Void, Void> {
        Place[] places;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                String jsonStr =
                        Utility.getJsonStringFromUrl(YQL_EUROPE_COUNTRIES_BASE);
                Log.d(this.getClass().getSimpleName(), "Url: " + jsonStr);

                if (jsonStr != null) {

                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject queryObject = jsonObject.getJSONObject("query");
                    JSONObject resultsObject = queryObject.getJSONObject("results");
                    JSONArray placeArray = resultsObject.getJSONArray("place");

                    places = new Place[placeArray.length()];
                    for (int i = 0; i < placeArray.length(); i++) {
                        JSONObject placeObject = placeArray.getJSONObject(i);
                        Place place = gson.fromJson(placeObject.toString(), Place.class);
                        places[i] = place;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(SplashScreenActivity.this, SplashScreenResultActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, places);
            startActivity(intent);
            finish();
        }
    }
}
