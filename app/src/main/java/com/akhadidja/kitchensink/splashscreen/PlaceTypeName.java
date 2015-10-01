package com.akhadidja.kitchensink.splashscreen;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceTypeName implements Parcelable {
    String code;
    String content;

    public PlaceTypeName(String code, String content) {
        this.code = code;
        this.content = content;
    }

    @Override
    public String toString() {
        return "PlaceTypeName{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.content);
    }

    protected PlaceTypeName(Parcel in) {
        this.code = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<PlaceTypeName> CREATOR = new Parcelable.Creator<PlaceTypeName>() {
        public PlaceTypeName createFromParcel(Parcel source) {
            return new PlaceTypeName(source);
        }

        public PlaceTypeName[] newArray(int size) {
            return new PlaceTypeName[size];
        }
    };
}
