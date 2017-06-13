package com.target.dealbrowserpoc.dealbrowser.dealitemdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deallist.DealListActivity;

/**
 * An activity representing a single Deal Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * deal item details are presented side-by-side with a list of deal items
 * in a {@link DealListActivity}.
 */

public class DealItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_item_detail);
//        // Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(DealItemDetailFragment.ARG_DEAL_ITEM,
                    getIntent().getParcelableExtra(DealItemDetailFragment.ARG_DEAL_ITEM));
            DealItemDetailFragment fragment = new DealItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.deal_item_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
