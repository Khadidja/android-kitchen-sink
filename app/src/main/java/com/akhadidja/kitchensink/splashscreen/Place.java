package com.akhadidja.kitchensink.splashscreen;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    String lang;
    String uri;
    String woeid;
    PlaceTypeName placeTypeName;
    String name;

    public Place(String lang, String uri, String woeid, PlaceTypeName placeTypeName, String name) {
        this.lang = lang;
        this.uri = uri;
        this.woeid = woeid;
        this.placeTypeName = placeTypeName;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "lang='" + lang + '\'' +
                ", uri='" + uri + '\'' +
                ", woeid='" + woeid + '\'' +
                ", placeTypeName=" + placeTypeName +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lang);
        dest.writeString(this.uri);
        dest.writeString(this.woeid);
        dest.writeParcelable(this.placeTypeName, 0);
        dest.writeString(this.name);
    }

    protected Place(Parcel in) {
        this.lang = in.readString();
        this.uri = in.readString();
        this.woeid = in.readString();
        this.placeTypeName = in.readParcelable(PlaceTypeName.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
