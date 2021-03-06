package com.binary.giphy.ui.gifview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.base.MvpView;

/**
 * Created by duong on 10/18/2017.
 */

public interface GifViewMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void getGif(Activity activity, String url, String name);
}

