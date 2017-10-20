package com.binary.giphy.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.binary.giphy.R;
import com.binary.giphy.base.BasePresenter;
import com.binary.giphy.base.MvpPresenter;
import com.binary.giphy.base.MvpView;
import com.binary.giphy.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

/**
 * Created by duong on 10/11/2017.
 */

public class LoginPresenter<V extends MvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {
    private FirebaseAuth auth;
    @Inject
    public LoginPresenter() {
    }

    @Override
    public void loginValidate(final Context context, String username, final String password) {
        auth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, "Enter your username", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Enter your password", Toast.LENGTH_LONG).show();
        }

        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(context, R.string.password_length, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, R.string.auth_failed, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            context.startActivity(new Intent(context, MainActivity.class));
                            ((Activity) context).finish();
                        }
                    }
                });
    }
}
