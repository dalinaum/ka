package com.kakaobank.assignment.images;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.kakaobank.assignment.entity.Document;
import com.kakaobank.assignment.entity.util.DocumentUtil;
import com.kakaobank.assignment.entity.util.RealmUtil;

import io.realm.Realm;

public class DocumentPresentation extends BaseObservable {
    @Bindable
    public String collection;

    @Bindable
    public String displaySiteName;

    @Bindable
    public String imageUrl;

    @Bindable
    public String docUrl;

    @Bindable
    public boolean isFavorite;

    public void setDocument(Document document) {
        collection = document.collection;
        displaySiteName = document.displaySiteName;
        docUrl = document.docUrl;
        imageUrl = document.imageUrl;
        isFavorite = document.isFavorite;
    }

    public void onClick(View _) {
        Realm realm = RealmUtil.getRealmInstance();
        DocumentUtil.toggleFavorite(realm, docUrl);
        realm.close();
    }
}
