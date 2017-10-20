package com.binary.giphy.ui.register;

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

public class RegisterPresenter<V extends MvpView> extends BasePresenter<V> implements RegisterMvpPresenter<V> {
    private FirebaseAuth auth;
    @Inject
    public RegisterPresenter() {

    }

    @Override
    public void registerValidate(final Context context, String username, String password) {
        auth = FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(context,"Enter your username", Toast.LENGTH_LONG).show();
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"Enter your password", Toast.LENGTH_LONG).show();
        }

        if(password.length()<6){
            Toast.makeText(context, R.string.password_length, Toast.LENGTH_LONG).show();
        }

        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(context, "Register Failed", Toast.LENGTH_LONG).show();
                        } else {
                            context.startActivity(new Intent(context, MainActivity.class));
                            ((Activity) context).finish();
                        }
                    }
                });
    }
}
