package com.akhadidja.kitchensink.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhadidja.kitchensink.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private String [] mOptions;
    private Context mContext;

    public DrawerAdapter(Context context, String[] options) {
        mContext = context;
        mOptions = options;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.nav_drawer_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.optionTextView.setText(mOptions[position]);
    }

    @Override
    public int getItemCount() {
        return mOptions.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView optionTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            optionTextView = (TextView) itemView.findViewById(R.id.option_textView);
        }
    }
}
