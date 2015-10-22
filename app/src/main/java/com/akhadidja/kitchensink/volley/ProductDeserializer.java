package com.akhadidja.kitchensink.volley;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ProductDeserializer implements JsonDeserializer<Product> {

    private static final String LOG_TAG = ProductDeserializer.class.getSimpleName();

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if(json.isJsonNull()){
            Log.d(LOG_TAG, "json is null");
            return new Product();
        }
        final JsonObject jsonObject = json.getAsJsonObject();
        String condition = null;
        String customerReviewAverage = null;
        int customerReviewCount = -1;
        String manufacturer = null;
        String name = null;
        double regularPrice = -1;
        double salePrice = -1;
        String image = null;

        if(jsonObject.get("condition") != null)
            condition = jsonObject.get("condition").getAsString();
        if (jsonObject.get("customerReviewAverage") != null)
            customerReviewAverage = jsonObject.get("customerReviewAverage").getAsString();
        if (jsonObject.get("customerReviewCount") != null)
            customerReviewCount = jsonObject.get("customerReviewCount").getAsInt();
        if(jsonObject.get("manufacturer") != null)
            manufacturer = jsonObject.get("manufacturer").getAsString();
        if(jsonObject.get("name") != null)
            name = jsonObject.get("name").getAsString();
        if(jsonObject.get("regularPrice") != null)
            regularPrice = jsonObject.get("regularPrice").getAsDouble();
        if(jsonObject.get("salePrice") != null)
            salePrice = jsonObject.get("salePrice").getAsDouble();
        if(jsonObject.get("thumbnailImage") != null)
            image = jsonObject.get("image").getAsString();

        Product product = new Product();
        product.setCondition(condition);
        product.setCustomerReviewAverage(customerReviewAverage);
        product.setCustomerReviewCount(customerReviewCount);
        product.setManufacturer(manufacturer);
        product.setName(name);
        product.setRegularPrice(regularPrice);
        product.setSalePrice(salePrice);
        product.setImage(image);

        return product;
    }
}
