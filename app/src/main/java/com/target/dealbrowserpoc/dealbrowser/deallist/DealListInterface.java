package com.target.dealbrowserpoc.dealbrowser.deallist;

import com.target.dealbrowserpoc.dealbrowser.MVP.MVP;

/**
 * MVP interfaces for Deal List
 */

public class DealListInterface {

    public interface View extends MVP.View {

        public void showLoadingDialog();
        public void dismissLoadingDialog();
    }

    public interface Presenter extends MVP.Presenter {
    }

}
