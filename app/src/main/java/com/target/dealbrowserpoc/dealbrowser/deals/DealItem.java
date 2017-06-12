package com.target.dealbrowserpoc.dealbrowser.deals;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DealItem implements Parcelable {
    int index;
    String id;
    String title;
    String description;
    @SerializedName("price")
    String originalPrice;
    String salePrice;
    @SerializedName("image")
    String imageUrl;
    String aisle;

    public DealItem(int index, String id, String title, String description, String originalPrice, String salePrice, String imageUrl, String aisle) {
        this.index = index;
        this.id = id;
        this.title = title;
        this.description = description;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.imageUrl = imageUrl;
        this.aisle = aisle;
    }

//    public Bitmap getProductImage(Context context){
//        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), image);
//        return bmp;
//    }

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAisle() {
        return aisle;
    }

    @Override
    public String toString() {
        return title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.index);
        dest.writeString(this.originalPrice);
        dest.writeString(this.salePrice);
        dest.writeString(this.imageUrl);
        dest.writeString(this.aisle);
    }

    protected DealItem(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.index = in.readInt();
        this.originalPrice = in.readString();
        this.salePrice = in.readString();
        this.imageUrl = in.readString();
        this.aisle = in.readString();
    }

    public static final Parcelable.Creator<DealItem> CREATOR = new Parcelable.Creator<DealItem>() {
        @Override
        public DealItem createFromParcel(Parcel source) {
            return new DealItem(source);
        }

        @Override
        public DealItem[] newArray(int size) {
            return new DealItem[size];
        }
    };
}