package com.binary.giphy.ui;

import android.content.Context;

import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.models.Category;

import java.util.List;

/**
 * Created by duong on 9/27/2017.
 */

public interface MainMvpPresenter<V extends MainView> extends MvpPresenter<V> {
    List<Category> loadDataFiles(Context context);
}
