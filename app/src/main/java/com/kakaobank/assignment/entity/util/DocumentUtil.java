package com.kakaobank.assignment.entity.util;

import com.kakaobank.assignment.entity.Document;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DocumentUtil {

    public static RealmResults<Document> getAllDocuments(Realm realm) {
        return realm.where(Document.class).findAllAsync();
    }

    public static void addDocuments(Realm realm, List<Document> documents) {
        realm.executeTransaction(r -> {
            r.copyToRealmOrUpdate(documents);
        });
    }
}
