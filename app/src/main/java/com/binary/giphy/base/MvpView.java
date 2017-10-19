package com.binary.giphy.base;

import android.app.ProgressDialog;

/**
 * Created by duong on 9/20/2017.
 */

public interface MvpView {
    void showProgress(ProgressDialog progressDialog);

    void hideProgress(ProgressDialog progressDialog);
}
