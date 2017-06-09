package com.target.dealbrowserpoc.dealbrowser;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealListItemAdapter extends RecyclerView.Adapter<DealListItemAdapter.DealItemViewHolder> {
    private List<DealItem> dealItems;
    private Context context;


    public static DealListItemAdapter newInstance(Context context, List<DealItem> items) {
        return new DealListItemAdapter(context, items);
    }

    protected DealListItemAdapter(Context ctx, List<DealItem> items) {
        super();
        context = ctx;
        dealItems = items;
    }

    @Override
    public DealItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deal_list_item,parent, false);
        return new DealItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DealItemViewHolder holder, int position) {
        DealItem dealItem = getItem(position);
        if (dealItem != null) {
            holder.title.setText(dealItem.getTitle());
            holder.price.setText(dealItem.getOriginalPrice());
            if (!TextUtils.isEmpty(dealItem.imageUrl)) {
                // TODO: Trying to invalidate so we get new images since each DealItem from network has same URI.  Doesn't totally work - maybe try using my own OkHttp with no caching.
                // This would not normally be a problem since uri would be unique.
                Picasso
                        .with(context)
                        .invalidate(dealItem.imageUrl);
                Picasso
                        .with(context)
                        .load(dealItem.imageUrl)
                        .into(holder.productImage);
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
        @BindView(R.id.deal_list_item_image_view)
        public ImageView productImage;

        @BindView(R.id.deal_list_item_title)
        public TextView title;

        @BindView(R.id.deal_list_item_price)
        public TextView price;

        public DealItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
