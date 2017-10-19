package com.binary.giphy.base;

/**
 * Created by duong on 9/20/2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private  V mMvpView;

    @Override
    public void attachView(V view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached(){
        return mMvpView != null;
    }

    public V getmMvpView(){
        return mMvpView;
    }
}
