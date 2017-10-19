package com.binary.giphy.ui.gifview;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpView;

import javax.inject.Inject;

/**
 * Created by duong on 10/18/2017.
 */

public class GifViewPresenter<V extends MvpView>  extends BasePresenter<V> implements GifViewMvpPresenter<V>{
    @Inject
    public GifViewPresenter() {
    }
}
