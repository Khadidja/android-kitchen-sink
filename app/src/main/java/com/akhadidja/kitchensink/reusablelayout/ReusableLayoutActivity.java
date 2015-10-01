package com.akhadidja.kitchensink.reusablelayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.akhadidja.kitchensink.R;

public class ReusableLayoutActivity extends AppCompatActivity {

    private static final String STATE_PETS_POSITION = "pets_gallery_position";
    private static final String STATE_FOOD_POSITION = "food_gallery_position";
    private static final String SHARED_PREF = "My_Pref";
    GallerySpinner petsGallerySpinner;
    GallerySpinner foodGallerySpinner;

    private int [] petsImageIds = {R.drawable.animal_pet_cute_cat_small,
            R.drawable.animal_sitting_animals_inside_small,
            R.drawable.bulldog_small,
            R.drawable.jumping_cute_playing_animals_small,
            R.drawable.love_animal_dog_pet_small};

    private int [] foodImageIds = {R.drawable.food_ice_cream,
            R.drawable.food_kitchen_cutting_board_cooking,
            R.drawable.food_plate_rucola_salad,
            R.drawable.food_salad_healthy_vegetables};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reusable_layout);
        petsGallerySpinner = (GallerySpinner) findViewById(R.id.pets_gallery_spinner);
        petsGallerySpinner.setImages(petsImageIds);
        foodGallerySpinner = (GallerySpinner) findViewById(R.id.food_gallery_spinner);
        foodGallerySpinner.setImages(foodImageIds);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        petsGallerySpinner.setImagePosition(sharedPref.getInt(STATE_PETS_POSITION, 0));
        foodGallerySpinner.setImagePosition(sharedPref.getInt(STATE_FOOD_POSITION, 0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(STATE_PETS_POSITION, petsGallerySpinner.getPosition());
        editor.putInt(STATE_FOOD_POSITION, foodGallerySpinner.getPosition());
        editor.apply();
    }
}
