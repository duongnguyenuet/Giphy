package com.binary.giphy.ui.login;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.base.MvpView;

import javax.inject.Inject;

/**
 * Created by duong on 10/11/2017.
 */

public class LoginPresenter<V extends MvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    @Inject
    public LoginPresenter() {
    }
}
