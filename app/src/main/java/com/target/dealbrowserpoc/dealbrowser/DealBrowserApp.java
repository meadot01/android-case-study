package com.target.dealbrowserpoc.dealbrowser;

import android.app.Application;

import com.target.dealbrowserpoc.dealbrowser.injection.components.DaggerNetworkComponent;
import com.target.dealbrowserpoc.dealbrowser.injection.components.NetworkComponent;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.NetworkModule;

/**
 * Created by T Meadows on 6/7/17.
 */

public class DealBrowserApp extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
