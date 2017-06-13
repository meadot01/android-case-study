package com.target.dealbrowserpoc.dealbrowser.deallist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.target.dealbrowserpoc.dealbrowser.DealBrowserApp;
import com.target.dealbrowserpoc.dealbrowser.MVP.MVPActivity;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;
import com.target.dealbrowserpoc.dealbrowser.injection.components.DaggerDealListComponent;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.DealListModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class DealListActivity  extends MVPActivity<DealListInterface.Presenter> implements DealListInterface.View {
//public class DealListActivity  extends AppCompatActivity {

    private static final String TAG="DealListActivity";

    private ProgressDialog loadingIndicator;

    private boolean twoPaneLayout = false;

    @BindView(R.id.item_list)
    RecyclerView itemListRecyclerView;

//    @Inject
//    Observable<DealResponse> dealItemObservable;

    private DealListItemAdapter dealListItemAdapter;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //((DealBrowserApp)getApplication()).getNetworkComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerDealListComponent.builder()
                .networkComponent(((DealBrowserApp)getApplication()).getNetworkComponent())
                .dealListModule(new DealListModule(this))
                .build().inject(this);
        getPresenter().start();

        compositeDisposable = new CompositeDisposable();

        itemListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        itemListRecyclerView.addItemDecoration(new DividerItemDecoration(this, android.support.v7.widget.DividerItemDecoration.VERTICAL));

        itemListRecyclerView.setHasFixedSize(true);

        if (findViewById(R.id.deal_item_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPaneLayout = true;
        }

        fetchDealData();

    }

    private void fetchDealData() {

        DisposableObserver<DealResponse> observer = new DisposableObserver<DealResponse>() {
            @Override
            public void onNext(@NonNull DealResponse dealResponse) {
                if (dealResponse != null && dealResponse.getDealItemList() != null) {
                    dealListItemAdapter = new DealListItemAdapter(DealListActivity.this, dealResponse.getDealItemList());
                    itemListRecyclerView.setAdapter(dealListItemAdapter);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"Error Fetching Deal Items", e);
                Snackbar.make(itemListRecyclerView, "Error fetching deal items "+ e.getLocalizedMessage(), Snackbar.LENGTH_INDEFINITE).show();
            }

            @Override
            public void onComplete() {

            }
        };
        compositeDisposable.add(observer);
//        dealItemObservable
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(observer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Don't create the menu for now
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public boolean isTwoPaneLayout() {
        return twoPaneLayout;
    }

    @Override
    public void showLoadingDialog() {
        dismissLoadingDialog();
        loadingIndicator = new ProgressDialog(this);
        loadingIndicator.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loadingIndicator.setMessage("Loading. Please wait...");
        loadingIndicator.setIndeterminate(true);
        loadingIndicator.setCanceledOnTouchOutside(false);
        loadingIndicator.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (loadingIndicator != null) {
            loadingIndicator.dismiss();
        }
    }
}
