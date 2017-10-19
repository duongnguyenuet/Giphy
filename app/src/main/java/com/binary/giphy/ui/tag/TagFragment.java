package com.binary.giphy.ui.tag;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binary.giphy.MyApplication;
import com.binary.giphy.R;
import com.binary.giphy.models.Category;
import com.binary.giphy.ui.MainActivity;
import com.binary.giphy.ui.subtag.SubTagFragment;
import com.binary.giphy.utils.Utils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends Fragment implements TagView {
    public static final String ARG_CATE_NAME = "ARG_CATE_NAME";
    public static final String ARG_CATE_SUB = "ARG_CATE_SUB";
    private ArrayList<Category> arrCategory = new ArrayList<>();
    private TagAdapter adapter;

    @Inject
    TagPresenter<TagView> tagPresenter;

    @BindView(R.id.rv_tags)
    RecyclerView rvTags;

    private MainActivity mainActivity;

    public TagFragment() {
        // Required empty public constructor
    }

    public static TagFragment newInstance(Category category){
        TagFragment tagFragment = new TagFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_CATE_NAME, category.getName());
        bundle.putStringArrayList(ARG_CATE_SUB, (ArrayList<String>) category.getSub());
        tagFragment.setArguments(bundle);
        return tagFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tag,container,false);
        ButterKnife.bind(this,view);
        mainActivity = (MainActivity) getActivity();

        Category category = new Category();
        category.setName(getArguments().getString(ARG_CATE_NAME));
        category.setSub(getArguments().getStringArrayList(ARG_CATE_SUB));

        initPresenter();
        init(category);
        return view;
    }

    private void init(final Category category){
        adapter = new TagAdapter(mainActivity,category.getSub());
        rvTags.setAdapter(adapter);
        rvTags.setLayoutManager(new GridLayoutManager(mainActivity,2));
        adapter.setOnItemClickListener(new TagAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Utils.slideFragment(SubTagFragment.newInstance(category.getSub().get(position)), mainActivity.getSupportFragmentManager());
            }
        });
    }

    private void initPresenter(){
        MyApplication application = (MyApplication) getActivity().getApplication();
        application.getAppComponent().inject(this);
        tagPresenter.attachView(this);
    }

    @Override
    public void showProgress(ProgressDialog progressDialog) {

    }

    @Override
    public void hideProgress(ProgressDialog progressDialog) {

    }

    @Override
    public void showCategory(ArrayList<Category> categories) {
        arrCategory.addAll(categories);
        adapter.notifyDataSetChanged();
    }
}
