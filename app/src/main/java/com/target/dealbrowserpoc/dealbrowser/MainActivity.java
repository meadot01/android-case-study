package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.deals.DealResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends Activity {

    @BindView(R.id.item_list)
    RecyclerView itemListRecyclerView;

    @Inject
    Observable<DealResponse> dealItemObservable;

    private List<DealItem> dealItemList = new ArrayList<>();
    private DealListItemAdapter dealListItemAdapter;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((DealBrowserApp)getApplication()).getNetworkComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        compositeDisposable = new CompositeDisposable();

        itemListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        itemListRecyclerView.addItemDecoration(new DividerItemDecoration(this, android.support.v7.widget.DividerItemDecoration.VERTICAL));

        itemListRecyclerView.setHasFixedSize(true);

        fetchDealData();

    }

    private void fetchDealData() {

        compositeDisposable.add(dealItemObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<DealResponse>() {
                    @Override
                    public void accept(@NonNull DealResponse dealResponse) throws Exception {
                        if (dealResponse != null && dealResponse.getDealItemList() != null) {
                            dealListItemAdapter = new DealListItemAdapter(MainActivity.this, dealResponse.getDealItemList());
                            itemListRecyclerView.setAdapter(dealListItemAdapter);
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error fetching deal items "+ throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe());
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
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
