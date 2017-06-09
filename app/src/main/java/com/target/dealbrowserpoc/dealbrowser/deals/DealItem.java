package com.target.dealbrowserpoc.dealbrowser.deals;

import com.google.gson.annotations.SerializedName;

public class DealItem {
    public int index;
    public String id;
    public String title;
    public String description;
    @SerializedName("price")
    public String originalPrice;
    public String salePrice;
    transient public int image;
    @SerializedName("image")
    public String imageUrl;
    public String aisle;

    public DealItem(int index, String id, String title, String description, String originalPrice, String salePrice, int image, String imageUrl, String aisle) {
        this.index = index;
        this.id = id;
        this.title = title;
        this.description = description;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.image = image;
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

    public int getImage() {
        return image;
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
}