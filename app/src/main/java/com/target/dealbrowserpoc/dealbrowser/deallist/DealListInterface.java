package com.target.dealbrowserpoc.dealbrowser.deallist;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVP;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

import java.util.List;

/**
 * MVP interfaces for Deal List
 */

public class DealListInterface {

    public interface View extends MVP.View {

        void showLoadingDialog();
        void dismissLoadingDialog();
        void loadItems(List<DealItem> dealItemList, ListType listType);
    }

    public interface Presenter extends MVP.Presenter {
        void toggleListType();
    }

}
