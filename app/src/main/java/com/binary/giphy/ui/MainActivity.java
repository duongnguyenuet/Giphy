package com.binary.giphy.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.binary.giphy.API.GiphyAPI;
import com.binary.giphy.MyApplication;
import com.binary.giphy.R;
import com.binary.giphy.base.BaseActivity;
import com.binary.giphy.models.Category;
import com.binary.giphy.ui.gifupload.GifUploadActivity;
import com.binary.giphy.ui.login.LoginActivity;
import com.binary.giphy.ui.sharedgif.SharedGifActivity;
import com.binary.giphy.ui.tag.TagFragment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {
    @Inject
    MainPresenter<MainView> mMainPresenter;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.fab_menu)
    FloatingActionMenu fabMenu;
    @BindView(R.id.btn_logout)
    FloatingActionButton btnLogout;
    @BindView(R.id.btn_upload)
    FloatingActionButton btnUpload;
    @BindView(R.id.btn_yourgif)
    FloatingActionButton btnUrGif;

    private LinearLayout mLinearLayout;
    private List<Category> categories;
    private FirebaseAuth auth;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GifUploadActivity.class));
            }
        });

        btnUrGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharedGifActivity.class));
            }
        });

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
        tabs.setViewPager(viewPager);
        setUpTabColor();
    }

    private void initPresenter(){
        getAppComponent().inject(this);
        mMainPresenter.attachView(this);
    }

    private void initProgress(){
        dialog = new ProgressDialog(this);
        dialog.setMessage("Get gifs");
    }

    private void setUpTabColor(){
        mLinearLayout = (LinearLayout) tabs.getChildAt(0);
        for(int i=0; i < mLinearLayout.getChildCount(); i++){
            TextView textView = (TextView) mLinearLayout.getChildAt(i);
            textView.setTextSize(14);
            switch (i){
                case 0: textView.setTextColor(Color.parseColor("#EF5350"));
                    break;
                case 1: textView.setTextColor(Color.parseColor("#AB47BC"));
                    break;
                case 2: textView.setTextColor(Color.parseColor("#81C784"));
                    break;
                case 3: textView.setTextColor(Color.parseColor("#4FC3F7"));
                    break;
                case 4: textView.setTextColor(Color.parseColor("#DCE775"));
                    break;
                case 5: textView.setTextColor(Color.parseColor("#90A4AE"));
                    break;
                case 6: textView.setTextColor(Color.parseColor("#7986CB"));
                    break;
                case 7: textView.setTextColor(Color.parseColor("#AED581"));
                    break;
                case 8: textView.setTextColor(Color.parseColor("#FFB74D"));
                    break;
                case 9: textView.setTextColor(Color.parseColor("#F06292"));
                    break;
                case 10: textView.setTextColor(Color.parseColor("#303F9F"));
                    break;
                case 11: textView.setTextColor(Color.parseColor("#7B1FA2"));
                    break;
                case 12: textView.setTextColor(Color.parseColor("#00796B"));
                    break;
                case 13: textView.setTextColor(Color.parseColor("#689F38"));
                    break;
                case 14: textView.setTextColor(Color.parseColor("#FFA000"));
                    break;
                case 15: textView.setTextColor(Color.parseColor("#2979FF"));
                    break;
                case 16: textView.setTextColor(Color.parseColor("#C6FF00"));
                    break;
                case 17: textView.setTextColor(Color.parseColor("#D32F2F"));
                    break;
                case 18: textView.setTextColor(Color.parseColor("#1976D2"));
                    break;
                case 19: textView.setTextColor(Color.parseColor("#1DE9B6"));
                    break;
                case 20: textView.setTextColor(Color.parseColor("#76FF03"));
                    break;
                case 21: textView.setTextColor(Color.parseColor("#FF3D00"));
                    break;
                case 22: textView.setTextColor(Color.parseColor("#FF1744"));
                    break;
                case 23: textView.setTextColor(Color.parseColor("#D500F9"));
                    break;
                case 24: textView.setTextColor(Color.parseColor("#3D5AFE"));
                    break;
            }
        }
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
