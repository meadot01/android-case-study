package com.target.dealbrowserpoc.dealbrowser.MVP;

import android.util.Log;

/**
 * Abstract base Presenter that implements some MVP methods
 */

 abstract public class MVPPresenter<ViewType extends MVP.View> implements MVP.Presenter {

     private static final String TAG = "MVPPresenter";

     ViewType view;

     // private constructor to prevent non-view constructor
     private MVPPresenter() {
     }

     public MVPPresenter(ViewType view) {
         this.view = view;
     }

    @Override
    public void start() {

    }

    @Override
     public void destroy() {
         Log.v(TAG, "destroy()");
         view = null;
     }

     protected ViewType getView() {
         return view;
     }
 }
