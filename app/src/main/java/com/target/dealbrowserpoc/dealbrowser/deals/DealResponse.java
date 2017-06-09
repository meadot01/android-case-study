package com.target.dealbrowserpoc.dealbrowser.deals;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by T Meadows on 6/8/17.
 */

public class DealResponse {

    @SerializedName("data")
    public List<DealItem> dealItemList;

    public List<DealItem> getDealItemList() {
        return dealItemList;
    }
}
