package com.binary.giphy.di.module;

import com.binary.giphy.API.ApiConstants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duong on 9/20/2017.
 */
@Module
public class NetModule {
    public static Retrofit retrofit = null;

    static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build();

    @Provides
    @Singleton
    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
