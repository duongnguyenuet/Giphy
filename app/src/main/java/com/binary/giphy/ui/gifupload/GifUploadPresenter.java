package com.binary.giphy.ui.gifupload;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpView;
import com.binary.giphy.models.GifUpload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by duong on 10/18/2017.
 */

public class GifUploadPresenter<V extends MvpView> extends BasePresenter<V> implements GifUploadMvpPresenter<V> {
    public static final String FB_STORAGE_PATH = "image/";
    @Inject
    public GifUploadPresenter() {
    }

    @Override
    public void uploadGif(final Context context, final DatabaseReference mDatabaseRef, StorageReference mStorageRef, Uri imgUri) {
        if(imgUri != null){
            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setTitle("Uploading");
            dialog.show();

            StorageReference reference = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(imgUri, context));
            reference.putFile(imgUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            dialog.dismiss();
                            Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
                            GifUpload gifUpload = new GifUpload(taskSnapshot.getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(gifUpload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage("Uploading" + (int)progress + "%");
                        }
                    });
        } else {
            Toast.makeText(context, "Please select your gif", Toast.LENGTH_SHORT).show();
        }
    }

    public String getImageExt(Uri uri, Context context){
        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
