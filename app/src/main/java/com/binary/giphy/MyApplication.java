package com.binary.giphy;

import android.app.Application;

import com.binary.giphy.di.component.AppComponent;
import com.binary.giphy.di.component.DaggerAppComponent;
import com.binary.giphy.di.component.DaggerNetComponent;
import com.binary.giphy.di.component.NetComponent;
import com.binary.giphy.di.module.AppModule;
import com.binary.giphy.di.module.NetModule;

/**
 * Created by duong on 9/27/2017.
 */

public class MyApplication extends Application {
    private NetComponent mNetComponent;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public void initComponent(){
        mNetComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .netComponent(mNetComponent)
                .build();
    }
}
