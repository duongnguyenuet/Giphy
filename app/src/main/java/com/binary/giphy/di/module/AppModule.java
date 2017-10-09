package com.binary.giphy.di.module;

import com.binary.giphy.di.ActivityScope;
import com.binary.giphy.ui.MainMvpPresenter;
import com.binary.giphy.ui.MainView;
import com.binary.giphy.ui.subtag.SubTagMvpPresenter;
import com.binary.giphy.ui.subtag.SubTagView;
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
    MainMvpPresenter<MainView> provideMainPresenter(MainMvpPresenter<MainView> presenter){
        return presenter;
    }

    @Provides
    @ActivityScope
    TagMvpPresenter<TagView> provideTagPresenter(TagMvpPresenter<TagView> presenter){
        return presenter;
    }

    @Provides
    @ActivityScope
    SubTagMvpPresenter<SubTagView> provideSubTagPresenter(SubTagMvpPresenter<SubTagView> presenter){
        return presenter;
    }
}
