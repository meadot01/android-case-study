package com.target.dealbrowserpoc.dealbrowser.dealitemdetail;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVP;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

/**
 * MVP interfaces for Deal item detail
 */

public interface DealItemDetailInterface {

    // No model needed for detail.  Normally would have model to handle storage of cart and list if they
    // actually did something.
//    interface Model extends MVP.Model {
//
//    }

    interface View extends MVP.View {
        public void showToastMessage(int messageResId, Object... messageParameters);
        public DealItem getDealItem();

    }

    interface Presenter extends MVP.Presenter {
        public void addToListPressed();
        public void addToCartPressed();
    }

}
