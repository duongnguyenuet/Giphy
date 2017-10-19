package com.binary.giphy.di.component;

import com.binary.giphy.di.ActivityScope;
import com.binary.giphy.di.module.AppModule;
import com.binary.giphy.ui.MainActivity;
import com.binary.giphy.ui.gifupload.GifUploadActivity;
import com.binary.giphy.ui.gifview.GifViewActivity;
import com.binary.giphy.ui.login.LoginActivity;
import com.binary.giphy.ui.register.RegisterActivity;
import com.binary.giphy.ui.reset.ResetActivity;
import com.binary.giphy.ui.sharedgif.SharedGifActivity;
import com.binary.giphy.ui.subtag.SubTagAdapter;
import com.binary.giphy.ui.subtag.SubTagFragment;
import com.binary.giphy.ui.tag.TagFragment;

import dagger.Component;

/**
 * Created by duong on 9/27/2017.
 */

@ActivityScope
@Component(dependencies = NetComponent.class, modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(TagFragment tagFragment);

    void inject(SubTagFragment subTagFragment);

    void inject(LoginActivity loginActivity);

    void inject(ResetActivity resetActivity);

    void inject(RegisterActivity registerActivity);

    void inject(GifUploadActivity gifUploadActivity);

    void inject(SharedGifActivity sharedGifActivity);

    void inject(GifViewActivity activity);

}
