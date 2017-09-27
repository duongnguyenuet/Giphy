package com.binary.giphy.API;

import com.binary.giphy.models.searchdetail.Meta;
import com.binary.giphy.models.searchdetail.Pagination;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by duong on 9/20/2017.
 */

public class ApiResponse<T> {
    @SerializedName("data")
    @Expose
    private T data = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
