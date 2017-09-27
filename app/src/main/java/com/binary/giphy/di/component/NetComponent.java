package com.binary.giphy.di.component;

import com.binary.giphy.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by duong on 9/20/2017.
 */
@Singleton
@Component(modules = NetModule.class)
public interface NetComponent {
    Retrofit retrofit();
}
