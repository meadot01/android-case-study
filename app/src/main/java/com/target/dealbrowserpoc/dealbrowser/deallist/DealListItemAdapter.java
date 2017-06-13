package com.target.dealbrowserpoc.dealbrowser.deallist;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.dealitemdetail.DealItemDetailActivity;
import com.target.dealbrowserpoc.dealbrowser.dealitemdetail.DealItemDetailFragment;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealListItemAdapter extends RecyclerView.Adapter<DealListItemAdapter.DealItemViewHolder> {
    private List<DealItem> dealItems;
    private Context context;
    private ListType listType;


    protected DealListItemAdapter(Context ctx, List<DealItem> items, ListType listType) {
        super();
        context = ctx;
        dealItems = items;
        this.listType = listType;
    }

    @Override
    public DealItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (listType == ListType.LIST) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deal_list_item,parent, false);

        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deal_grid_item,parent, false);

        }
        return new DealItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DealItemViewHolder holder, int position) {
        final DealItem dealItem = getItem(position);
        if (dealItem != null) {
            holder.title.setText(dealItem.getTitle());
            if (!TextUtils.isEmpty(dealItem.getSalePrice())) {
                holder.price.setText(dealItem.getSalePrice());
            } else {
                holder.price.setText(dealItem.getOriginalPrice());
            }
            if (!TextUtils.isEmpty(dealItem.getImageUrl())) {
                // TODO: Trying to invalidate so we get new images since each DealItem from network has same URI.  Doesn't totally work - maybe try using my own OkHttp with no caching.
                // This would not normally be a problem since uri would be unique.
                Picasso
                        .with(context)
                        .invalidate(dealItem.getImageUrl());
                Picasso
                        .with(context)
                        .load(dealItem.getImageUrl())
                        .into(holder.productImage);
            }
            holder.dealListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((DealListActivity)context).isTwoPaneLayout()) {
                        Bundle arguments = new Bundle();
                        arguments.putParcelable(DealItemDetailFragment.ARG_DEAL_ITEM, dealItem);
                        DealItemDetailFragment fragment = new DealItemDetailFragment();
                        fragment.setArguments(arguments);
                        ((DealListActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.deal_item_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DealItemDetailActivity.class);
                        intent.putExtra(DealItemDetailFragment.ARG_DEAL_ITEM, dealItem);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            // If JellyBean or higher use transition animations
                            Pair<View, String> p1 = Pair.create((View)holder.productImage, context.getString(R.string.transition_name_deal_image));
                            Pair<View, String> p2 = Pair.create((View)holder.title, context.getString(R.string.transition_name_deal_title));
                            Pair<View, String> p3 = Pair.create((View)holder.price, context.getString(R.string.transition_name_deal_price));
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation((DealListActivity)context, p1, p2, p3);
                            context.startActivity(intent, options.toBundle());
                        } else {
                            context.startActivity(intent);
                        }
                    }
                }
            });
            if (TextUtils.isEmpty(dealItem.getAisle())) {
                holder.location.setVisibility(View.INVISIBLE);
                holder.orLabel.setVisibility(View.INVISIBLE);
            } else {
                holder.location.setVisibility(View.VISIBLE);
                holder.location.setText(dealItem.getAisle());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (dealItems == null) {
            return 0;
        }
        return dealItems.size();
    }

    public DealItem getItem(int position){
        try {
            return dealItems.get(position);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public class DealItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.deal_list_item)
        public ViewGroup dealListItem;

        @BindView(R.id.deal_list_item_image_view)
        public ImageView productImage;

        @BindView(R.id.deal_list_item_title)
        public TextView title;

        @BindView(R.id.deal_list_item_price)
        public TextView price;

        @BindView(R.id.deal_list_item_location)
        public TextView location;

        @BindView(R.id.or_label)
        public TextView orLabel;

        public DealItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
