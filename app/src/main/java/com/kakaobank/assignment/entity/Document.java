package com.kakaobank.assignment.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Document extends RealmObject {
    public String collection;

    @SerializedName("thumbnail_url")
    public String thumbnailUrl;

    @SerializedName("image_url")
    public String imageUrl;

    public int width;

    public int height;

    @SerializedName("display_sitename")
    public String displaySiteName;

    @PrimaryKey
    @SerializedName("doc_url")
    public String docUrl;

    @SerializedName("date_time")
    public String dateTime;

    public boolean isFavorite;
}
