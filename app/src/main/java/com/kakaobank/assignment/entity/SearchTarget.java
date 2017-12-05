package com.kakaobank.assignment.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SearchTarget extends RealmObject {
    @PrimaryKey
    public int id;
    public String keyword;
    public int page;

    public SearchTarget() {
    }

    public SearchTarget(int id, String keyword, int page) {
        this.id = id;
        this.keyword = keyword;
        this.page = page;
    }
}
