package com.binary.giphy.ui.gifview;

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

public class GifViewPresenter<V extends MvpView>  extends BasePresenter<V> implements GifViewMvpPresenter<V>{
    @Inject
    public GifViewPresenter() {
    }

    @Override
    public void getGif(final Context context, String url) {
        String path = Environment.getExternalStorageDirectory().getPath()
                + "/" + Environment.DIRECTORY_DCIM + "/";
        getDownloadObservable(url, path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isSuccessful) throws Exception {
                        if (isSuccessful) {
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private Observable<Boolean> getDownloadObservable(final String urlImage, final String path) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    URL url = new URL(urlImage);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    File file = new File(path);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream outputStream = new FileOutputStream(file);
                    byte b[] = new byte[1024];
                    int count = inputStream.read(b);
                    while (count != -1) {
                        outputStream.write(b, 0, count);
                        count = inputStream.read(b);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
    }
}
