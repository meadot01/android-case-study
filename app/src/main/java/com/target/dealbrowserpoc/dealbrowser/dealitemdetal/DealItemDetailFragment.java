package com.target.dealbrowserpoc.dealbrowser.dealitemdetal;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.MVP.MVPFragment;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deallist.DealListActivity;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.injection.components.DaggerDealItemDetailComponent;
import com.target.dealbrowserpoc.dealbrowser.injection.modules.DealItemDetailModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a single Deal item detail screen.
 * This fragment is either contained in a {@link DealListActivity}
 * in two-pane mode (on tablets) or a {@link DealItemDetailActivity}
 * on handsets.
 */

public class DealItemDetailFragment extends MVPFragment<DealItemDetailInterface.Presenter> implements DealItemDetailInterface.View {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_DEAL_ITEM = "deal_item";


    /**
     * The deal itemcontent this fragment is presenting.
     */
    private DealItem dealItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DealItemDetailFragment() {
    }

    @BindView(R.id.deal_item_title_text)
    TextView itemName;
    @BindView(R.id.deal_item_price_text)
    TextView salePriceText;
    @BindView(R.id.deal_item_orig_price_text)
    TextView originalPriceText;
    @BindView(R.id.deal_item_orig_price_label)
    TextView originalPriceLabel;
    @BindView(R.id.deal_item_description)
    TextView itemDescription;
    @BindView(R.id.deal_item_image)
    ImageView itemImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DaggerDealItemDetailComponent.builder()
                .dealItemDetailModule(new DealItemDetailModule(this))
                .build()
                .inject(this);
        getPresenter().start();

        if (getArguments().containsKey(ARG_DEAL_ITEM)) {
            dealItem = getArguments().getParcelable(ARG_DEAL_ITEM);
            this.getActivity().setTitle(dealItem.getTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deal_item_detail, container, false);
        ButterKnife.bind(this, rootView);
        if (dealItem != null) {
            itemName.setText(dealItem.getTitle());
            if(TextUtils.isEmpty(dealItem.getSalePrice())) {
                originalPriceText.setVisibility(View.INVISIBLE);
                originalPriceLabel.setVisibility(View.INVISIBLE);
                salePriceText.setText(dealItem.getOriginalPrice());
            } else {
                originalPriceText.setVisibility(View.VISIBLE);
                originalPriceLabel.setVisibility(View.INVISIBLE);
                salePriceText.setText(dealItem.getSalePrice());
                originalPriceText.setText(dealItem.getOriginalPrice());
                originalPriceText.setPaintFlags(originalPriceText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            itemDescription.setText(dealItem.getDescription());
            Picasso.with(getActivity())
                    .load(dealItem.getImageUrl())
                    .into(itemImage);
        }


        return rootView;
    }

    @OnClick(R.id.add_to_cart_button)
    void onCartButtonClicked() {
        getPresenter().addToCartPressed();
    }

    @OnClick(R.id.add_to_list_button)
    void onCartListClicked() {
        getPresenter().addToListPressed();
    }

    @Override
    public void showToastMessage(int messageResId, Object... messageParameters) {
        String messageString = getActivity().getString(messageResId, messageParameters);
        Toast.makeText(getActivity(), messageString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public DealItem getDealItem() {
        return dealItem;
    }
}
