package com.kakaobank.assignment.entity.util;

import com.kakaobank.assignment.entity.SearchTarget;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;

public class SearchTargetUtil {

    public static Flowable<RealmResults<SearchTarget>> getKeywordAndPageFlowable(Realm realm) {
        return realm.where(SearchTarget.class).equalTo("id", 0).findAllAsync().asFlowable()
                .filter(results -> results.isLoaded());
    }

    public static void setKeywordAndPageAsync(Realm realm, String keyword, int page, Realm.Transaction.OnSuccess onSuccess) {
        realm.executeTransactionAsync(r -> {
            SearchTarget searchTarget = new SearchTarget(0, keyword, page);
            r.copyToRealmOrUpdate(searchTarget);
        }, onSuccess);
    }

    public static void setKeywordAndPage(Realm realm, String keyword, int page) {
        realm.executeTransaction(r -> {
            SearchTarget searchTarget = new SearchTarget(0, keyword, page);
            r.copyToRealmOrUpdate(searchTarget);
        });
    }
}
