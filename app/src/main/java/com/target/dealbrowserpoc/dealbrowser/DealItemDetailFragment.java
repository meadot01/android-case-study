package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a single Deal item detail screen.
 * This fragment is either contained in a {@link MainActivity}
 * in two-pane mode (on tablets) or a {@link DealItemDetailActivity}
 * on handsets.
 */

public class DealItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_DEAL_ITEM = "deal_item";

    /**
     * The dummy content this fragment is presenting.
     */
    private DealItem dealItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DealItemDetailFragment() {
    }

    @BindView(R.id.item_detail_item_name)
    TextView itemName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_DEAL_ITEM)) {
            dealItem = getArguments().getParcelable(ARG_DEAL_ITEM);
            this.getActivity().setTitle(dealItem.getTitle());
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.content);
//            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deal_item_detail, container, false);
        ButterKnife.bind(this, rootView);
        if (dealItem != null) {
            itemName.setText(dealItem.getTitle());
        }


        return rootView;
    }

}
