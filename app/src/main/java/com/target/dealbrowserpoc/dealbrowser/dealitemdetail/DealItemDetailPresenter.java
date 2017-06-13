package com.target.dealbrowserpoc.dealbrowser.dealitemdetail;

import android.util.Log;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVPPresenter;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

import javax.inject.Inject;

/**
 * Deal item detail Presenter
 */

public class DealItemDetailPresenter extends MVPPresenter<DealItemDetailInterface.View> implements DealItemDetailInterface.Presenter {

    private final static String TAG = "DealItemDetailPresenter";

    @Inject
    public DealItemDetailPresenter(DealItemDetailInterface.View dealItemDetailView) {
        super(dealItemDetailView);
    }

    @Override
    public void start() {
        super.start();
        Log.v(TAG,"start()");
    }

    @Override
    public void addToListPressed() {
        // Would normally call model to save to list
        if (getView() != null) {
            DealItem dealItem = getView().getDealItem();
            if (dealItem != null) {
                getView().showToastMessage(R.string.added_to_list, dealItem.getTitle());
            }
        }
    }

    @Override
    public void addToCartPressed() {
        // Would normally call model to save to cart
        if (getView() != null) {
            DealItem dealItem = getView().getDealItem();
            if (dealItem != null) {
                getView().showToastMessage(R.string.added_to_cart, dealItem.getTitle());
            }
        }
    }
}
