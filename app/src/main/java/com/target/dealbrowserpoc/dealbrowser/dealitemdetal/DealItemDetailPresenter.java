package com.target.dealbrowserpoc.dealbrowser.dealitemdetal;

import android.util.Log;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVPPresenter;

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

}
