package com.binary.giphy.ui.subtag;

import com.binary.giphy.base.View;
import com.binary.giphy.models.searchdetail.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duong on 10/3/2017.
 */

public interface SubTagView extends View {
    void showData(List<Data> datas);
}
