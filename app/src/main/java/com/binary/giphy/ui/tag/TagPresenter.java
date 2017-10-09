package com.binary.giphy.ui.tag;

import com.binary.giphy.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by duong on 9/30/2017.
 */

public class TagPresenter<V extends TagView> extends BasePresenter<V> implements TagMvpPresenter<V> {
    @Inject
    public TagPresenter(){};
}
