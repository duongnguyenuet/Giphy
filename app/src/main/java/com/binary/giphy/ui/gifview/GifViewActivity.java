package com.binary.giphy.ui.gifview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.binary.giphy.R;
import com.binary.giphy.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;



public class GifViewActivity extends BaseActivity implements GifViewMvpView {
    public static final String INTENT_VIEW = "view";

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
        Log.e("Link", getIntent().getStringExtra(INTENT_VIEW));
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
                mPresenter.getGif(getApplication(), getIntent().getStringExtra(INTENT_VIEW));
            }
        });
    }
}
