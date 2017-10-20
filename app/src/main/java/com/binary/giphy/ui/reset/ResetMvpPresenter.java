package com.binary.giphy.ui.reset;

import android.content.Context;

import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.base.MvpView;

/**
 * Created by duong on 10/11/2017.
 */

public interface ResetMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void resetPassword(Context context, String email);
}
