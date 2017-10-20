package com.binary.giphy.ui.reset;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

/**
 * Created by duong on 10/11/2017.
 */

public class ResetPresenter<V extends MvpView> extends BasePresenter<V> implements ResetMvpPresenter<V> {
    private FirebaseAuth auth;
    @Inject
    public ResetPresenter() {

    }

    @Override
    public void resetPassword(final Context context, String email) {
        auth = FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(context,"Enter your username", Toast.LENGTH_LONG).show();
        }
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
