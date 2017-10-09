package com.binary.giphy.controller;

import android.content.Context;

import retrofit2.Response;

/**
 * Created by duong on 9/30/2017.
 */

public class HTTPController {
    private Context context;
    private static HTTPController httpControler;
    public static HTTPController getInstance(Context context){

        if (httpControler==null){
            httpControler = new HTTPController(context);
        }
        return httpControler;
    }

    private HTTPController(Context context) {
        this.context = context;
    }

    //kiểm tra response trả về
    public boolean isSuccess(Response response) {
        return response.isSuccessful();
    }
}
