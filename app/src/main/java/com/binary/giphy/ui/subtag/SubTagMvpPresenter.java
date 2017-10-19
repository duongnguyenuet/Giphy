package com.binary.giphy.ui.subtag;

import com.binary.giphy.base.MvpPresenter;

/**
 * Created by duong on 10/3/2017.
 */

public interface SubTagMvpPresenter<V extends SubTagMvpView> extends MvpPresenter<V> {
    void getData(String tag);
}
