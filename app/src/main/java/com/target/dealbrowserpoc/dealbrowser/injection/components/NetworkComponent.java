package com.target.dealbrowserpoc.dealbrowser.injection.components;

import com.target.dealbrowserpoc.dealbrowser.deallist.DealListActivity;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Observable;

/**
 * Created by T Meadows on 6/7/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    Observable<DealResponse> getItemList();
   // void inject(DealListActivity dealListActivity);

}
