package com.binary.giphy.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.binary.giphy.MyApplication;
import com.binary.giphy.di.component.AppComponent;

/**
 * Created by duong on 10/18/2017.
 */


public class BaseActivity extends AppCompatActivity implements MvpView {
    private AppComponent mAppComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void showProgress(ProgressDialog progressDialog) {

    }

    @Override
    public void hideProgress(ProgressDialog progressDialog) {

    }

    public AppComponent getAppComponent() {
        MyApplication application = (MyApplication) getApplication();
        return application.getAppComponent();
    }
}
