package com.binary.giphy.ui.subtag;

import com.binary.giphy.base.Presenter;

/**
 * Created by duong on 10/3/2017.
 */

public interface SubTagMvpPresenter<V extends SubTagView> extends Presenter<V> {
    void getData(String tag);
}
