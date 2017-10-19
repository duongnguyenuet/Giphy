package com.binary.giphy.base;

/**
 * Created by duong on 9/20/2017.
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
}
