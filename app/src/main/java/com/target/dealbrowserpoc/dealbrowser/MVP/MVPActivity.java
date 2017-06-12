package com.target.dealbrowserpoc.dealbrowser.MVP;

import android.support.v7.app.AppCompatActivity;

/**
 * Abstract base Fragment that implements some MVP methods
 */

abstract public class MVPActivity<PresenterType extends MVP.Presenter> extends AppCompatActivity {
    PresenterType presenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
