package com.binary.giphy.ui.login;

import android.content.Context;

import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.base.MvpView;

/**
 * Created by duong on 10/11/2017.
 */

public interface LoginMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void loginValidate(Context context, String username, String password);
}
