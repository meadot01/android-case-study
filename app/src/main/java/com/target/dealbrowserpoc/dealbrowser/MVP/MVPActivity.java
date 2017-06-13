package com.target.dealbrowserpoc.dealbrowser.MVP;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Abstract base Fragment that implements some MVP methods
 */

abstract public class MVPActivity<PresenterType extends MVP.Presenter> extends AppCompatActivity implements MVP.View {

    @Inject
    PresenterType presenter;

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    protected PresenterType getPresenter() {
        return presenter;
    }
}
