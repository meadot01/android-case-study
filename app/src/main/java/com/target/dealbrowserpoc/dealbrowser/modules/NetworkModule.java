package com.target.dealbrowserpoc.dealbrowser.modules;

import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;
import com.target.dealbrowserpoc.dealbrowser.remote.TargetDealsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by T Meadows on 6/7/17.
 */

@Module
public class NetworkModule {

    public static final String TARGET_BASE_URL = "http://target-deals.herokuapp.com/api/";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TARGET_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    Observable<DealResponse> provideItemList(Retrofit retrofit) {
        return retrofit
                .create(TargetDealsService.class)
                .getDealItems();
    }


}
