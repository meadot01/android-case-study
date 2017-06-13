package com.target.dealbrowserpoc.dealbrowser.deallist;

import android.util.Log;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVPPresenter;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Deal list Presenter
 */

public class DealListPresenter extends MVPPresenter<DealListInterface.View> implements DealListInterface.Presenter {

    private final static String TAG = "DealListPresenter";

    Observable<DealResponse> dealItemObservable;
    CompositeDisposable disposables = new CompositeDisposable();
    List<DealItem> dealItems;
    ListType listViewStyle = ListType.LIST;

    @Inject
    public DealListPresenter(Observable<DealResponse> dealItemObservable, DealListInterface.View dealListView) {
        super(dealListView);
        this.dealItemObservable = dealItemObservable;
    }

    @Override
    public void start() {
        super.start();
        Log.v(TAG,"start()");
        if (dealItems != null && dealItems.size() > 0) {
            showDealItems();
        } else {
            fetchData();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if (disposables != null) {
            disposables.clear();
        }
    }

    @Override
    public void toggleListType() {
        if (listViewStyle == ListType.LIST) {
            listViewStyle = ListType.GRID;
        } else {
            listViewStyle = ListType.LIST;
        }
        showDealItems();
    }

    private void fetchData() {

        getView().showLoadingDialog();
        disposables.add(dealItemObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<DealResponse>() {
                    @Override
                    public void onNext(@NonNull DealResponse dealResponse) {
                        Log.v(TAG,"Response received");
                        if (dealResponse != null && dealResponse.getDealItemList() != null) {
                            dealItems = dealResponse.getDealItemList();
                            showDealItems();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"Error Fetching Deal Items", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.v(TAG,"onComplete()");
                        getView().dismissLoadingDialog();
                    }
                }));
    }

    private void showDealItems() {
        if (dealItems != null) {
            getView().loadItems(dealItems, listViewStyle);
        }
    }


}
