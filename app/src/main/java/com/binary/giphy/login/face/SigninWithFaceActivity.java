package com.binary.giphy.login.face;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary.giphy.R;
import com.binary.giphy.utils.Utils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class SigninWithFaceActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView tv_info;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_signin_with_face);

        imageView = (ImageView)findViewById(R.id.img_avatar);

        callbackManager = CallbackManager.Factory.create();

        tv_info = (TextView)findViewById(R.id.tv_info);
        loginButton = (LoginButton)findViewById(R.id.btn_loginFace);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken = loginResult.getAccessToken().getToken();
                Log.e("toke Face", accessToken);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                Log.i("LoginActivity",
                                        response.toString());
                                try {
                                    String id = object.getString("id");
                                    try {
                                        String url = "http://graph.facebook.com/" + id + "/picture?type=large";
                                        Utils.loadImageFromUrl(url, imageView, getApplicationContext());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    String name = object.getString("name");
                                    tv_info.setText(name );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                tv_info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                tv_info.setText("Login attempt failed.");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
