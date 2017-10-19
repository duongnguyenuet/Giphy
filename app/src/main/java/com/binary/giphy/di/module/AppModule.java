package com.binary.giphy.di.module;

import com.binary.giphy.di.ActivityScope;
import com.binary.giphy.ui.MainMvpPresenter;
import com.binary.giphy.ui.MainView;
import com.binary.giphy.ui.gifupload.GifUploadMvpPresenter;
import com.binary.giphy.ui.gifupload.GifUploadMvpView;
import com.binary.giphy.ui.gifupload.GifUploadPresenter;
import com.binary.giphy.ui.gifview.GifViewMvpPresenter;
import com.binary.giphy.ui.gifview.GifViewMvpView;
import com.binary.giphy.ui.gifview.GifViewPresenter;
import com.binary.giphy.ui.login.LoginMvpPresenter;
import com.binary.giphy.ui.login.LoginMvpView;
import com.binary.giphy.ui.login.LoginPresenter;
import com.binary.giphy.ui.register.RegisterMvpPresenter;
import com.binary.giphy.ui.register.RegisterMvpView;
import com.binary.giphy.ui.register.RegisterPresenter;
import com.binary.giphy.ui.reset.ResetMvpPresenter;
import com.binary.giphy.ui.reset.ResetMvpView;
import com.binary.giphy.ui.reset.ResetPresenter;
import com.binary.giphy.ui.sharedgif.SharedGifMvpPresenter;
import com.binary.giphy.ui.sharedgif.SharedGifMvpView;
import com.binary.giphy.ui.sharedgif.SharedGifPresenter;
import com.binary.giphy.ui.subtag.SubTagMvpPresenter;
import com.binary.giphy.ui.subtag.SubTagMvpView;
import com.binary.giphy.ui.tag.TagMvpPresenter;
import com.binary.giphy.ui.tag.TagView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by duong on 9/27/2017.
 */
@Module
public class AppModule {
    @Provides
    @ActivityScope
    MainMvpPresenter<MainView> provideMainPresenter(MainMvpPresenter<MainView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    TagMvpPresenter<TagView> provideTagPresenter(TagMvpPresenter<TagView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    SubTagMvpPresenter<SubTagMvpView> provideSubTagPresenter(SubTagMvpPresenter<SubTagMvpView> presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    GifUploadMvpPresenter<GifUploadMvpView> provideGifUploadPresenter(GifUploadPresenter<GifUploadMvpView> gifUploadPresenter) {
        return gifUploadPresenter;
    }

    @Provides
    @ActivityScope
    GifViewMvpPresenter<GifViewMvpView> provideGifViewPresenter(GifViewPresenter<GifViewMvpView> gifViewPresenter) {
        return gifViewPresenter;
    }

    @Provides
    @ActivityScope
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> loginPresenter) {
        return loginPresenter;
    }

    @Provides
    @ActivityScope
    RegisterMvpPresenter<RegisterMvpView> provideRegisterPresenter(RegisterPresenter<RegisterMvpView> registerPresenter) {
        return registerPresenter;
    }

    @Provides
    @ActivityScope
    ResetMvpPresenter<ResetMvpView> provideResetPresenter(ResetPresenter<ResetMvpView> resetPresenter) {
        return resetPresenter;
    }

    @Provides
    @ActivityScope
    SharedGifMvpPresenter<SharedGifMvpView> provideSharedGifPresenter(SharedGifPresenter<SharedGifMvpView> sharedGifPresenter) {
        return sharedGifPresenter;
    }

}
