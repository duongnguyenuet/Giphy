package com.binary.giphy.ui.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.binary.giphy.MyApplication;
import com.binary.giphy.R;
import com.binary.giphy.base.BaseActivity;
import com.binary.giphy.ui.MainActivity;
import com.binary.giphy.ui.register.RegisterActivity;
import com.binary.giphy.ui.reset.ResetActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends BaseActivity implements LoginMvpView {
    @BindView(R.id.img_background)
    GifImageView imgBackground;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_forget)
    Button btnForget;
    @BindView(R.id.btn_reg)
    Button btnReg;

    private FirebaseAuth auth;
    @Inject
    LoginPresenter<LoginMvpView> mPresenter;

    //    private String URL_BACKGROUND = "https://media.giphy.com/media/OK5LK5zLFfdm/giphy.gif";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getAppComponent().inject(this);
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MyApplication application = (MyApplication) getApplication();
        application.getAppComponent();

        Glide.with(this)
                .load(R.drawable.giphy)
                .asGif()
                .fitCenter()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imgBackground);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loginValidate(LoginActivity.this, edtUsername.getText().toString(), edtPassword.getText().toString());
            }
        });
    }
}
