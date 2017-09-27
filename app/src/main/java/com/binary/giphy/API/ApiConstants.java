package com.binary.giphy.API;

/**
 * Created by duong on 9/20/2017.
 */

public class ApiConstants {
    public static final String BASE_URL_API = "http://api.giphy.com";
    public static final String API_KEY = "hlqQa9TZEy8dgNJvW7Cok2aZXT2P7O5A";
    public static final String API_SEARCH_BY_KEYWORD = "v1/gifs/search";


    //CODE
    public static final int CODE_SUCESS = 200;
    public static final int CODE_ERROR_PARAM = 404;
    public static final int CODE_ERROR_OVER_REQS = 429;
    public static final int CODE_ERROR_FORBIDDEN = 403;
    public static final int CODE_ERROR_SERVER = 500;
    public static final int CODE_ERROR_ACTIVED = 400;
    public static final int CODE_ERROR_LOGIN_PARAM = 300;
    public static final int CODE_ERROR_NO_EXIST_ACC = 402;
    public static final int CODE_ERROR_ALREADY_ACTIVE = 100;
    public static final int CODE_ERROR_LOCKED_ACC = 101;
    public static final int CODE_DEVICE_ANDROID = 1;
    public static final int CODE_CUSTOMER = 2;
    public static final int CODE_FINDING = 0;
    public static final int CODE_BIDED = 1;
    public static final int CODE_FINISH = 2;
    public static final int CODE_DECLINE = -1;
    public static final int CODE_TIME_OUT = -2;


    //LOGIN
    public static final String KEY_PHONE_NUMBER="phone_number";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_DEVICE_TOKEN="device_token";
    public static final String KEY_TYPE_DEVICE="type_device";
    public static final String KEY_TYPE_USER="type_user";


    //ACCESSTOKEN

}
