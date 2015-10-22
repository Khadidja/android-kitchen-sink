package com.akhadidja.kitchensink.volley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhadidja.kitchensink.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private ArrayList<Product> mProducts;
    private Context mContext;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        mContext = context;
        mProducts = products;
    }

    public void setProducts(ArrayList<Product> products){
        mProducts = products;
        notifyDataSetChanged();
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product = mProducts.get(position);

        Log.d(this.getClass().getSimpleName(), product.toString());
        Picasso.with(mContext).load(product.getImage()).into(holder.thumbnail);
        holder.name.setText(product.getName());
        holder.manufacturer.setText(product.getManufacturer());
        holder.condition.setText(product.getCondition());
        holder.price.setText(getPrice(product.getRegularPrice(), product.getSalePrice()));
        holder.review.setText(
                getReview(product.getCustomerReviewAverage(), product.getCustomerReviewCount()));
    }

    private String getReview(String customerReviewAverage, int customerReviewCount) {
        if(customerReviewAverage != null){
            return "Avg: " + customerReviewAverage + " (from " + customerReviewCount + " reviews)";
        } else {
            return "No reviews available";
        }
    }

    private String getPrice(double regularPrice, double salePrice) {
        if (salePrice < regularPrice)
            return "Sale! $" + salePrice;
        else
            return "$"+regularPrice;
    }


    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView name;
        TextView manufacturer;
        TextView condition;
        TextView price;
        TextView review;

        public ProductHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.product_thumbnail);
            name = (TextView) itemView.findViewById(R.id.product_name);
            manufacturer = (TextView) itemView.findViewById(R.id.product_manufacturer);
            condition = (TextView) itemView.findViewById(R.id.product_condition);
            price = (TextView) itemView.findViewById(R.id.product_price);
            review = (TextView) itemView.findViewById(R.id.product_review);
        }
    }
}
