package com.target.dealbrowserpoc.dealbrowser.injection.components;

import com.target.dealbrowserpoc.dealbrowser.deallist.DealListActivity;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;
import com.target.dealbrowserpoc.dealbrowser.injection.ActivityScope;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.DealListModule;

import dagger.Component;
import io.reactivex.Observable;

/**
 * Dagger Component for Deal List
 */

@ActivityScope
@Component(dependencies = NetworkComponent.class, modules = DealListModule.class)
public interface DealListComponent {

     void inject(DealListActivity dealListActivity);
}
