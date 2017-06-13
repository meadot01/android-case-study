package com.target.dealbrowserpoc.dealbrowser.deallist;

import android.util.Log;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVPPresenter;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Deal list Presenter
 */

public class DealListPresenter extends MVPPresenter<DealListInterface.View> implements DealListInterface.Presenter {

    private final static String TAG = "DealListPresenter";

    Observable<DealResponse> dealItemObservable;

    @Inject
    public DealListPresenter(Observable<DealResponse> dealItemObservable, DealListInterface.View dealListView) {
        super(dealListView);
        this.dealItemObservable = dealItemObservable;
    }

    @Override
    public void start() {
        super.start();
        Log.v(TAG,"start()");
    }
}
