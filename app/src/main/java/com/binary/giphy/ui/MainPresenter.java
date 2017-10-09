package com.binary.giphy.ui;

import android.content.Context;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.models.Category;
import com.binary.giphy.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by duong on 9/27/2017.
 */

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(){};

    @Override
    public List<Category> loadDataFiles(Context context) {
        List<Category> categories = new ArrayList<>();
        try {
            String jsonFile = Utils.loadJSONFromAsset(context, "categories.json");
            JSONObject obj = new JSONObject(jsonFile);
            JSONArray m_jArry = obj.getJSONArray("categories");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject temp = m_jArry.getJSONObject(i);
                Category cate = new Category();
                cate.setName(temp.getString("name"));

                JSONArray sub_arr = temp.getJSONArray("sub");
                List<String> sub = new ArrayList();
                for (int j = 0; j < sub_arr.length(); j++) {
                    JSONObject object = sub_arr.getJSONObject(j);
                    sub.add(object.getString("sub_name"));
                }

                cate.setSub(sub);
                categories.add(cate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
