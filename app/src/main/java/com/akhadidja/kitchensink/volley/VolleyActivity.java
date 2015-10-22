package com.akhadidja.kitchensink.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.akhadidja.kitchensink.R;
import com.akhadidja.kitchensink.splashscreen.DividerItemDecoration;

import java.util.ArrayList;

public class VolleyActivity extends AppCompatActivity implements ProductsLoaderListener {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Toolbar toolbar = (Toolbar) findViewById(R.id.volley_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Best Buy Laptops");

        mRecyclerView = (RecyclerView) findViewById(R.id.volley_recycler_view);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final String url = "https://api.bestbuy.com/v1/products((categoryPath.id=abcat0502000))?" +
                "apiKey=" + getString(R.string.BEST_BUY_API_KEY) + "&sort=condition.asc" +
                "&show=condition,customerReviewAverage,customerReviewCount,manufacturer,name," +
                    "regularPrice,salePrice,image&format=json";

        FetchProductsTask fetchProductsTask = new FetchProductsTask(url, this);
        fetchProductsTask.execute();
    }

    @Override
    public void onProductsLoaded(ArrayList<Product> products) {
        if(products != null){
            RecyclerView.Adapter mAdapter = new ProductAdapter(this, products);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
