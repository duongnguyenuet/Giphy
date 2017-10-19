package com.binary.giphy.ui.gifupload;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by duong on 10/18/2017.
 */

public class GifUploadPresenter<V extends MvpView> extends BasePresenter<V> implements GifUploadMvpPresenter<V> {
    @Inject
    public GifUploadPresenter() {
    }
}
