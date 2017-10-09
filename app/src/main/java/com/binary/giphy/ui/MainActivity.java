package com.binary.giphy.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.binary.giphy.API.GiphyAPI;
import com.binary.giphy.MyApplication;
import com.binary.giphy.R;
import com.binary.giphy.models.Category;
import com.binary.giphy.ui.tag.TagFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {
    @Inject
    MainPresenter<MainView> mMainPresenter;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private TextView title;
    private List<Category> categories;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        View view = getLayoutInflater().inflate(R.layout.tab_one,null);
        title = view.findViewById(R.id.tab_title);
        getSupportActionBar().setElevation(0);
        initPresenter();
        init();
    }

    private void init(){
        categories = mMainPresenter.loadDataFiles(this);
        List<Fragment> fragments = new ArrayList<>();
        for(int i = 0; i < categories.size(); i++){
            fragments.add(TagFragment.newInstance(categories.get(i)));
        }
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager(),this,fragments,categories));
        tabs.setupWithViewPager(viewPager);
        title.setText(this.getString(R.string.tab_one));
        tabs.getTabAt(1).setCustomView(R.layout.tab_one);
//        for(int i=0; i < tabs.getTabCount(); i++){
////            switch (i){
////                case 0: title.setText("ACTIONS");
////                    break;
////                case 1: title.setText("ADJECTIVES");
////                    break;
////                default: break;
////            }
//            tabs.getTabAt(i).setCustomView(R.layout.tab_one);
//        }
    }

    private void initPresenter(){
        MyApplication application = (MyApplication) getApplication();
        application.getAppComponent().inject(this);
        mMainPresenter.attachView(this);
    }

    private void initProgress(){
        dialog = new ProgressDialog(this);
        dialog.setMessage("Get gifs");
    }

    @Override
    public void showProgress(ProgressDialog progressDialog) {
        dialog.show();
    }

    @Override
    public void hideProgress(ProgressDialog progressDialog) {
        dialog.hide();
    }
}
