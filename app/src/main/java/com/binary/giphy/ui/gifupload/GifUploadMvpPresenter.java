package com.binary.giphy.ui.gifupload;

import android.content.Context;
import android.net.Uri;

import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.base.MvpView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

/**
 * Created by duong on 10/18/2017.
 */

public interface GifUploadMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void uploadGif(Context context, DatabaseReference mDatabaseRef, StorageReference mStorageRef, Uri imgUri);
}
