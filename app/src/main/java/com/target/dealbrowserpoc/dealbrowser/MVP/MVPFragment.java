package com.target.dealbrowserpoc.dealbrowser.MVP;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * Abstract base Fragment that implements some MVP methods
 */

abstract public class MVPFragment<PresenterType extends MVP.Presenter> extends Fragment implements MVP.View {

    @Inject
    protected PresenterType presenter;



    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
