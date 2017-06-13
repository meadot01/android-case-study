package com.target.dealbrowserpoc.dealbrowser.deallist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
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
    private RecyclerView.LayoutManager linearLayoutManager;
    private RecyclerView.LayoutManager gridLayoutManager;

    private boolean twoPaneLayout = false;

    @BindView(R.id.item_list)
    RecyclerView itemListRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gridLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager = new LinearLayoutManager(this);
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
    }

    @Override
    public void loadItems(List<DealItem> dealItemList, ListType listType) {
        DealListItemAdapter dealListItemAdapter = new DealListItemAdapter(DealListActivity.this, dealItemList, listType);
        itemListRecyclerView.setAdapter(dealListItemAdapter);
        itemListRecyclerView.setLayoutManager(listType == ListType.LIST ? linearLayoutManager : gridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toggle_grid_list) {
            getPresenter().toggleListType();
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
        loadingIndicator.setMessage(getString(R.string.loading_message));
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
