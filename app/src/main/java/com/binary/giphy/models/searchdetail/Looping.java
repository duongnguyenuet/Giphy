package com.binary.giphy.models.searchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by duong on 9/19/2017.
 */

public class Looping {
    @SerializedName("mp4")
    @Expose
    private String mp4;

    public Looping(String mp4) {
        this.mp4 = mp4;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }
}
