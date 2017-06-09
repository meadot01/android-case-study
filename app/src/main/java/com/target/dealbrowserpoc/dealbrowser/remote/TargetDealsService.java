package com.target.dealbrowserpoc.dealbrowser.remote;

import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by T Meadows on 6/8/17.
 */

public interface TargetDealsService {

    @GET("deals")
    Observable<DealResponse> getDealItems();
}
