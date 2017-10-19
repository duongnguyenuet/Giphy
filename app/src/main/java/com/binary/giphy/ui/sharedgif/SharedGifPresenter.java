package com.binary.giphy.ui.sharedgif;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpView;

import javax.inject.Inject;

/**
 * Created by duong on 10/18/2017.
 */

public class SharedGifPresenter<V extends MvpView> extends BasePresenter<V> implements SharedGifMvpPresenter<V> {
    @Inject
    public SharedGifPresenter() {

    }
}
