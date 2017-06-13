package com.target.dealbrowserpoc.dealbrowser.injection.modules;

import com.target.dealbrowserpoc.dealbrowser.dealitemdetail.DealItemDetailInterface;
import com.target.dealbrowserpoc.dealbrowser.dealitemdetail.DealItemDetailPresenter;
import com.target.dealbrowserpoc.dealbrowser.injection.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for DealItemDetailScreen
 */

@Module
public class DealItemDetailModule {

    private final DealItemDetailInterface.View dealItemDetailView;

    public DealItemDetailModule(DealItemDetailInterface.View dealItemDetailView) {
        this.dealItemDetailView = dealItemDetailView;
    }

    @Provides
    @FragmentScope
    DealItemDetailInterface.View provideDealItemDetailView() {
        return dealItemDetailView;
    }

    @Provides
    @FragmentScope
    DealItemDetailInterface.Presenter provideDealItemDetailPresenter(DealItemDetailInterface.View dealItemDetailView) {
        return new DealItemDetailPresenter(dealItemDetailView);
    }
}
