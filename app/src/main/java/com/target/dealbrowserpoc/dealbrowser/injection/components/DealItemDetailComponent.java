package com.target.dealbrowserpoc.dealbrowser.injection.components;

import com.target.dealbrowserpoc.dealbrowser.dealitemdetail.DealItemDetailFragment;
import com.target.dealbrowserpoc.dealbrowser.injection.FragmentScope;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.DealItemDetailModule;

import dagger.Component;

/**
 * Dagger Component for Deal item detail
 */

@FragmentScope
@Component(modules = DealItemDetailModule.class)
public interface DealItemDetailComponent {
    void inject(DealItemDetailFragment dealItemDetailFragment);
}
