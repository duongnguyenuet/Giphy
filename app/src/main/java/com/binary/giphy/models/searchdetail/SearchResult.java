package com.binary.giphy.models.searchdetail;

import com.binary.giphy.models.searchdetail.Data;
import com.binary.giphy.models.searchdetail.Meta;
import com.binary.giphy.models.searchdetail.Pagination;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by duong on 9/19/2017.
 */

public class SearchResult {
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
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
