package com.binary.giphy.ui.subtag;

import com.binary.giphy.API.ApiConstants;
import com.binary.giphy.API.ApiResponse;
import com.binary.giphy.API.CallBackCustom;
import com.binary.giphy.API.GiphyAPI;
import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.interfaces.OnResponse;
import com.binary.giphy.models.searchdetail.Data;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by duong on 10/3/2017.
 */

public class SubTagPresenter<V extends SubTagView> extends BasePresenter<V> implements SubTagMvpPresenter<V> {
    private Retrofit retrofit;

    @Inject
    public SubTagPresenter(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void getData(String tag) {
//        Call<ApiResponse<List<Data>>> call = retrofit.create(GiphyAPI.class).getSearchByKeyResult(tag, ApiConstants.API_KEY, 25, 0, "y", "en", "json");
//        call.enqueue(new CallBackCustom<ApiResponse<List<Data>>>(new OnResponse<ApiResponse<List<Data>>>() {
//            @Override
//            public void onResponse(ApiResponse<List<Data>> response) {
//                List<Data> datas = response.getData();
//                getmMvpView().showData(datas);
//            }
//        }));
    }
}
