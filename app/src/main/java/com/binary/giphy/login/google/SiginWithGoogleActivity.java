package com.binary.giphy.login.google;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binary.giphy.R;
import com.binary.giphy.utils.Utils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class SiginWithGoogleActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{
    //SiginButton
    private SignInButton btn_SiginGoogle;
    //Signing Options
    private GoogleSignInOptions gso;
    //google api client
    private GoogleApiClient mGoogleApiClient;
    //Signin constant to check the activity result
    private final int RESULT_SIGN_IN = 100;

    private TextView tv_name;
    private ImageView img_avatar;
    private Button btn_signOut;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_google);

        btn_SiginGoogle = (SignInButton)findViewById(R.id.btn_SingIn);
        tv_name = (TextView)findViewById(R.id.tv_userName);
        img_avatar = (ImageView)findViewById(R.id.img_avatar);
        btn_signOut = (Button)findViewById(R.id.btn_logout);
        btn_signOut.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing in...");

        inItSigInGoogle();



    }

    //in it sigin button with google
    private void inItSigInGoogle(){
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        btn_SiginGoogle.setSize(SignInButton.SIZE_WIDE);
        btn_SiginGoogle.setScopes(gso.getScopeArray());

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        btn_SiginGoogle.setOnClickListener(SiginWithGoogleActivity.this);
    }

    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RESULT_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin

        if (requestCode == RESULT_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
            progressDialog.dismiss();

        }else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();

            //Displaying name and email
            tv_name.setText(acct.getDisplayName() + " " + acct.getEmail());

            try {
                Utils.loadImageFromUrl(acct.getPhotoUrl().toString(), img_avatar, this);
            }catch (Exception e){
                e.printStackTrace();
            }
            Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
            updateUi(true);
        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_SingIn:{
                signIn();
                progressDialog.show();
                break;
            }
            case R.id.btn_logout:{
                signOut();
                progressDialog.show();
                break;
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void signOut() {
        Log.e("cuongph", "signout fail!");
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUi(false);
                        progressDialog.dismiss();
                        Log.e("cuongph", "signout");
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly. We can try and retrieve an
            // authentication code.

            Log.d("GOT", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Checking sign in state...");
            progressDialog.show();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    progressDialog.dismiss();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void updateUi(boolean isSignin){
        if (isSignin){
            btn_signOut.setVisibility(View.VISIBLE);
            btn_SiginGoogle.setVisibility(View.GONE);
            tv_name.setVisibility(View.VISIBLE);
            img_avatar.setVisibility(View.VISIBLE);
        }else {
            btn_signOut.setVisibility(View.GONE);
            btn_SiginGoogle.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.GONE);
            img_avatar.setVisibility(View.GONE);
        }
    }
}
