package com.akhadidja.kitchensink.reusablelayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.akhadidja.kitchensink.R;

public class GallerySpinner extends LinearLayout {

    private static final String SUPER_KEY = "super";
    private static final String POSITION_KEY = "position";
    private ImageButton mPreviousButton;
    private ImageButton mNextButton;
    private int [] mImages = null;
    private int mPosition = -1;

    public GallerySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public GallerySpinner(Context context) {
        super(context);
        init(context);
    }

    public GallerySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.gallery_spinner_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mPreviousButton = (ImageButton) findViewById(R.id.gallery_spinner_view_previous);
        mNextButton = (ImageButton) findViewById(R.id.gallery_spinner_view_next);

        mPreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition -= 1;
                setImagePosition(mPosition);
            }
        });

        mNextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition += 1;
                setImagePosition(mPosition);
            }
        });

        setImagePosition(0);
    }

    public void setImages (int [] images){
        mImages =  images;
        setImagePosition(0);
    }

    public void setImagePosition(int position){
        mPosition = position;
        if(!canPosition())
            return;

        ImageView currentImage = (ImageView) findViewById(R.id.gallery_spinner_view_current_imageView);
        currentImage.setImageResource(mImages[mPosition]);

        if(mPosition == 0)
            mPreviousButton.setVisibility(INVISIBLE);
        else
            mPreviousButton.setVisibility(VISIBLE);

        if(mPosition == (mImages.length - 1))
            mNextButton.setVisibility(INVISIBLE);
        else
            mNextButton.setVisibility(VISIBLE);
    }

    private boolean canPosition(){
        return (mImages != null && mImages.length != 0
                && mPosition > -1 && mPosition < mImages.length);
    }

    public int getPosition(){
        return mPosition;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Log.d(this.getClass().getSimpleName(), "onSaveInstanceState");
                Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_KEY, super.onSaveInstanceState());
        bundle.putInt(POSITION_KEY, mPosition);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Log.d(this.getClass().getSimpleName(), "onRestoreInstanceState");
        if(state != null && state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(SUPER_KEY));
            setImagePosition(bundle.getInt(POSITION_KEY));

        } else
            super.onRestoreInstanceState(state);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        Log.d(this.getClass().getSimpleName(), "dispatchSaveInstanceState");
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        Log.d(this.getClass().getSimpleName(), "dispatchRestoreInstanceState");
        super.dispatchThawSelfOnly(container);
    }
}
