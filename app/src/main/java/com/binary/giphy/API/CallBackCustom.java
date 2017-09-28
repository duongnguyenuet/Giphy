package com.binary.giphy.API;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.binary.giphy.R;
import com.binary.giphy.interfaces.OnResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by duong on 9/20/2017.
 */

public class CallBackCustom<T> implements Callback<T> {
    OnResponse<T> t;
    Context context;

    public CallBackCustom(OnResponse<T> t, Context context) {
        this.t = t;
        this.context = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            t.onResponse(response.body());


        } else {
            //// TODO: 4/16/2017 Error . We need to inform to user check error here. Should be informed by dialog
            new    MaterialDialog.Builder(context).title("Error")
                    .content(String.format(context.getString(R.string.unknow_error_code),response.code()+""))
                    .build().show();
//            RLog.e("Error onRespone " + response.code());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
