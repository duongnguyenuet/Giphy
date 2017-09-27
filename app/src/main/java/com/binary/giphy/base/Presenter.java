package com.binary.giphy.base;

/**
 * Created by duong on 9/20/2017.
 */

public interface Presenter<V extends View> {
    void attachView(V view);
    void detachView();
}
