package com.target.dealbrowserpoc.dealbrowser.dealitemdetal;

import javax.inject.Inject;

/**
 * Deal item detail Presenter
 */

public class DealItemDetailPresenter implements DealItemDetailInterface.Presenter {

    DealItemDetailInterface.View dealItemDetailView;

    @Inject
    public DealItemDetailPresenter(DealItemDetailInterface.View dealItemDetailView) {
        this.dealItemDetailView = dealItemDetailView;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
