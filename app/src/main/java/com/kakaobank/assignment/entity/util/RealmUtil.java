package com.kakaobank.assignment.entity.util;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmUtil {

    public static Realm getRealmInstance() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded() // It's for NetworkComponentWithGSON
                .build();
        return Realm.getInstance(realmConfiguration);
    }
}
