package com.target.dealbrowserpoc.dealbrowser.deallist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.target.dealbrowserpoc.dealbrowser.DealBrowserApp;
import com.target.dealbrowserpoc.dealbrowser.MVP.MVPActivity;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.injection.components.DaggerDealListComponent;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.DealListModule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DealListActivity  extends MVPActivity<DealListInterface.Presenter> implements DealListInterface.View {

    private static final String TAG="DealListActivity";

    private ProgressDialog loadingIndicator;

    private boolean twoPaneLayout = false;

    @BindView(R.id.item_list)
    RecyclerView itemListRecyclerView;


    private DealListItemAdapter dealListItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //((DealBrowserApp)getApplication()).getNetworkComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



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

        DaggerDealListComponent.builder()
                .networkComponent(((DealBrowserApp)getApplication()).getNetworkComponent())
                .dealListModule(new DealListModule(this))
                .build().inject(this);
        getPresenter().start();

//        fetchDealData();

    }

    @Override
    public void loadItems(List<DealItem> dealItemList) {
        dealListItemAdapter = new DealListItemAdapter(DealListActivity.this, dealItemList);
        itemListRecyclerView.setAdapter(dealListItemAdapter);
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
