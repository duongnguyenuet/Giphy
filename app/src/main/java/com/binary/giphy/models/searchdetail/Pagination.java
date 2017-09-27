package com.binary.giphy.models.searchdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by duong on 9/19/2017.
 */

public class Pagination {
    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("total_count")
    @Expose
    private int total_count;
    @SerializedName("count")
    @Expose
    private int count;

    public Pagination(int offset, int total_count, int count) {
        this.offset = offset;
        this.total_count = total_count;
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
