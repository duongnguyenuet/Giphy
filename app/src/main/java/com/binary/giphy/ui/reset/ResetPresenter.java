package com.binary.giphy.ui.reset;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpView;

import javax.inject.Inject;

/**
 * Created by duong on 10/11/2017.
 */

public class ResetPresenter<V extends MvpView> extends BasePresenter<V> implements ResetMvpPresenter<V> {
    @Inject
    public ResetPresenter() {

    }
}
