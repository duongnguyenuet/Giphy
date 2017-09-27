package com.binary.giphy.models.searchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by duong on 9/19/2017.
 */

public class Meta {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("response_id")
    @Expose
    private String responseId;

    public Meta(String msg, int status, String responseId) {
        this.msg = msg;
        this.status = status;
        this.responseId = responseId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }
}
