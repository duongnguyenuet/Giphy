package com.binary.giphy.ui.gifview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.binary.giphy.API.ApiConstants;
import com.binary.giphy.API.CallBackCustom;
import com.binary.giphy.API.GiphyAPI;
import com.binary.giphy.R;
import com.binary.giphy.base.BaseActivity;
import com.binary.giphy.di.module.NetModule;
import com.binary.giphy.interfaces.OnResponse;
import com.binary.giphy.models.randomresult.RandomResult;
import com.binary.giphy.utils.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;


public class GifViewActivity extends BaseActivity implements GifViewMvpView {
    public static final String INTENT_VIEW = "view";
    public static final String TAG_NAME = "tag";
    public static final String SUBTAG_NAME  = "name";

    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.img_logo)
    GifImageView imgLogo;
    @BindView(R.id.gif_view)
    GifImageView gifView;
    @BindView(R.id.btn_fb)
    ImageButton btnFacebook;
    @BindView(R.id.btn_download)
    ImageButton btnDownload;
    @BindView(R.id.rv_related)
    RecyclerView rvRelated;

    RelatedGifAdapter adapter;
    ShareDialog shareDialog;
    @Inject
    GifViewPresenter<GifViewMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_gif_view);

        getAppComponent().inject(this);
        mPresenter.attachView(this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        shareDialog = new ShareDialog(this);
        Glide.with(this)
                .load(R.drawable.ic_view)
                .asGif()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgLogo);
        Glide.with(this)
                .load(getIntent().getStringExtra(INTENT_VIEW))
                .asGif()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(gifView);
        init(getIntent().getStringExtra(TAG_NAME));

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,getIntent().getStringExtra(INTENT_VIEW) );
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getGif(GifViewActivity.this, getIntent().getStringExtra(INTENT_VIEW), getIntent().getStringExtra(SUBTAG_NAME));
            }
        });
    }

    private void init(String tag){
        Call<RandomResult> call = NetModule.getClient().create(GiphyAPI.class).getSearchRandomResult(Constants.API_KEY, tag, "y", "json");
        call.enqueue(new CallBackCustom<RandomResult>(getApplication(), new OnResponse<RandomResult>() {
            @Override
            public void onResponse(final RandomResult response) {
                if(response.getMeta().getStatus() == ApiConstants.CODE_SUCESS){
                    final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    rvRelated.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            manager.invalidateSpanAssignments();
                            manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                        }
                    });
                    rvRelated.setLayoutManager(manager);
                    adapter = new RelatedGifAdapter(getApplication(), response);
                    rvRelated.setAdapter(adapter);
                    rvRelated.setHasFixedSize(true);
                    adapter.setOnItemClickListener(new RelatedGifAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            Intent intent = getIntent();
                            intent.putExtra(INTENT_VIEW, response.getData().getImageUrl());
                            intent.putExtra(TAG_NAME,response.getData().getCaption());
                            finish();
                            startActivity(intent);
                        }
                    });
                }
            }
        }));
    }
}
