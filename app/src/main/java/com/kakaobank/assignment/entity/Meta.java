package com.kakaobank.assignment.entity;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("total_count")
    public int totalCount;

    @SerializedName("pageable_count")
    public int pageableCount;

    @SerializedName("is_end")
    public boolean isEnd;
}
