package com.binary.giphy.ui.reset;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.binary.giphy.R;
import com.binary.giphy.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class ResetActivity extends BaseActivity implements ResetMvpView {
    @BindView(R.id.edt_username)
    EditText username;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.img_background)
    GifImageView imgBackground;
    @BindView(R.id.btn_back)
    Button btnBack;

    @Inject
    ResetPresenter<ResetMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reset);
        getAppComponent().inject(this);
        mPresenter.attachView(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        Glide.with(this)
                .load(R.drawable.giphy)
                .asGif()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgBackground);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.resetPassword(ResetActivity.this, username.getText().toString().trim());
            }
        });
    }
}
