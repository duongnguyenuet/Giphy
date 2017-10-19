package com.binary.giphy.ui.tag;

import com.binary.giphy.base.MvpView;
import com.binary.giphy.models.Category;

import java.util.ArrayList;

/**
 * Created by duong on 9/30/2017.
 */

public interface TagView extends MvpView {
    void showCategory(ArrayList<Category> categories);
}
