package com.target.dealbrowserpoc.dealbrowser.injection.modules;

import com.target.dealbrowserpoc.dealbrowser.deallist.DealListInterface;
import com.target.dealbrowserpoc.dealbrowser.deallist.DealListPresenter;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;
import com.target.dealbrowserpoc.dealbrowser.injection.ActivityScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

/**
 * Dagger module for DealListScreen
 */

@Module
public class DealListModule {

    private final DealListInterface.View dealListView;

    public DealListModule(DealListInterface.View dealListView) {
        this.dealListView = dealListView;
    }

    @Provides
    @ActivityScope
    DealListInterface.View provideDealListView() {
        return dealListView;
    }

    @Provides
    @ActivityScope
    DealListInterface.Presenter provideDealListPresenter(Observable<DealResponse> dealItemObservable, DealListInterface.View dealListView) {
        return new DealListPresenter(dealItemObservable, dealListView);
    }
}
