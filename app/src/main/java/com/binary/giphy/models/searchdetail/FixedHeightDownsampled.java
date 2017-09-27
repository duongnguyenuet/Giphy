package com.binary.giphy.models.searchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by duong on 9/19/2017.
 */

public class FixedHeightDownsampled {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("webp")
    @Expose
    private String webp;
    @SerializedName("webp_size")
    @Expose
    private String webpSize;

    public FixedHeightDownsampled(String url, String width, String height, String size, String webp, String webpSize) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.size = size;
        this.webp = webp;
        this.webpSize = webpSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWebp() {
        return webp;
    }

    public void setWebp(String webp) {
        this.webp = webp;
    }

    public String getWebpSize() {
        return webpSize;
    }

    public void setWebpSize(String webpSize) {
        this.webpSize = webpSize;
    }
}
