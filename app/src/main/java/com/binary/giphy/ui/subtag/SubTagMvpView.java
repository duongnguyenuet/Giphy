package com.binary.giphy.ui.subtag;

import com.binary.giphy.base.MvpView;
import com.binary.giphy.models.searchdetail.Data;

import java.util.List;

/**
 * Created by duong on 10/3/2017.
 */

public interface SubTagMvpView extends MvpView {
    void showData(List<Data> datas);
}
