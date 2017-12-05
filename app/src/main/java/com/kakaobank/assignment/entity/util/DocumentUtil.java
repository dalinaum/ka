package com.kakaobank.assignment.entity.util;

import com.kakaobank.assignment.entity.Document;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DocumentUtil {

    public static RealmResults<Document> getAllDocuments(Realm realm) {
        return realm.where(Document.class).findAllAsync();
    }

    public static RealmResults<Document> getFavorites(Realm realm) {
        return realm.where(Document.class).equalTo("isFavorite", true).findAllAsync();
    }

    public static void addDocuments(Realm realm, List<Document> documents) {
        realm.executeTransaction(r -> {
            r.copyToRealmOrUpdate(documents);
        });
    }

    public static void toggleFavorite(Realm realm, String docUrl) {
        realm.executeTransactionAsync(r -> {
            Document document = r.where(Document.class).equalTo("docUrl", docUrl).findFirst();
            document.isFavorite = !document.isFavorite;
        });
    }
}
