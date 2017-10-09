package com.binary.giphy.ui.subtag;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binary.giphy.API.ApiConstants;
import com.binary.giphy.API.ApiResponse;
import com.binary.giphy.API.CallBackCustom;
import com.binary.giphy.API.GiphyAPI;
import com.binary.giphy.MyApplication;
import com.binary.giphy.R;
import com.binary.giphy.di.module.NetModule;
import com.binary.giphy.interfaces.OnResponse;
import com.binary.giphy.models.searchdetail.Data;
import com.binary.giphy.ui.MainActivity;
import com.binary.giphy.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubTagFragment extends Fragment implements SubTagView {
    public static final String INTENT_TAG = "tag";
    private List<Data> dataList;
    private SubTagAdapter adapter;
    private MainActivity activity;

    @BindView(R.id.rv_sub_tag)
    RecyclerView rv_sub_tag;

    public SubTagFragment() {
        // Required empty public constructor
    }

    public static SubTagFragment newInstance(String tag) {
        Bundle args = new Bundle();
        args.putString(INTENT_TAG, tag);
        SubTagFragment fragment = new SubTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_tag, container, false);
        ButterKnife.bind(this,view);
        activity = (MainActivity) getActivity();

        MyApplication application = (MyApplication) getActivity().getApplication();
        application.getAppComponent().inject(this);
        init(getArguments().getString(INTENT_TAG));
        return view;
    }

    private void init(String tag){
        Call<ApiResponse<List<Data>>> call = NetModule.getClient().create(GiphyAPI.class).getSearchByKeyResult(Constants.API_KEY, tag, 25, 0, "y", "en", "json");
        call.enqueue(new CallBackCustom<ApiResponse<List<Data>>>(getContext(), new OnResponse<ApiResponse<List<Data>>>() {
            @Override
            public void onResponse(ApiResponse<List<Data>> response) {
                if(response.getMeta().getStatus() == ApiConstants.CODE_SUCESS){
                    rv_sub_tag.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    adapter = new SubTagAdapter(activity, response.getData());
                    rv_sub_tag.setAdapter(adapter);
                    rv_sub_tag.setHasFixedSize(true);
                }
            }
        }));
    }

    @Override
    public void showProgress(ProgressDialog progressDialog) {

    }

    @Override
    public void hideProgress(ProgressDialog progressDialog) {

    }

    @Override
    public void showData(List<Data> datas) {
        dataList.addAll(datas);
        adapter.notifyDataSetChanged();
    }
}
