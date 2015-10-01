package com.akhadidja.kitchensink.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhadidja.kitchensink.R;
import com.akhadidja.kitchensink.splashscreen.DividerItemDecoration;
import com.akhadidja.kitchensink.splashscreen.Place;

public class SplashScreenResultActivity extends AppCompatActivity {

    Place[] places;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_result);
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
            Parcelable [] parcelables = intent.getParcelableArrayExtra(Intent.EXTRA_TEXT);
            places = new Place[parcelables.length];
            for (int i = 0; i < places.length; i++) {
                places[i] = (Place) parcelables[i];
            }
            mRecyclerView = (RecyclerView) findViewById(R.id.splash_recycler_view);
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.ItemDecoration itemDecoration =
                    new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
            mRecyclerView.addItemDecoration(itemDecoration);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new SplashAdapter(places);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private class SplashAdapter extends RecyclerView.Adapter<SplashAdapter.ViewHolder> {
        private Place [] places;

        public SplashAdapter(Place[] places) {
            this.places = places;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.splash_result_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Place place = places[position];
            holder.name.setText(place.name);
            holder.type.setText("Classified as " + place.placeTypeName.content);
        }

        @Override
        public int getItemCount() {
            return places.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            TextView type;
            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.splash_place_name);
                type = (TextView) itemView.findViewById(R.id.splash_place_type);
            }
        }
    }
}
