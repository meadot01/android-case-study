package com.target.dealbrowserpoc.dealbrowser.components;

import com.target.dealbrowserpoc.dealbrowser.MainActivity;
import com.target.dealbrowserpoc.dealbrowser.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by T Meadows on 6/7/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity mainActivity);

}
