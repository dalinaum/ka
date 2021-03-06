package com.kakaobank.assignment.main;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.view.View;

import com.kakaobank.assignment.entity.SearchTarget;
import com.kakaobank.assignment.entity.util.RealmUtil;
import com.kakaobank.assignment.entity.util.SearchTargetUtil;
import com.kakaobank.assignment.util.SingleLiveEvent;

import io.realm.Realm;

public class MainPresentation extends ViewModel {
    private static final String TAG = "MainPresentation";
    private static final String DEFAULT_KEYWORD = "설현";
    private static final int DEFAULT_PAGE = 1;
    private final Realm realm;

    public ObservableField<String> keyword = new ObservableField<>();
    public SingleLiveEvent<String> searchKeywordEvent = new SingleLiveEvent<>();

    public MainPresentation() {
        realm = RealmUtil.getRealmInstance();

        SearchTarget keywordAndPage = SearchTargetUtil.getKeywordAndPage(realm);
        if (keywordAndPage == null) {
            initKeywordAndPage();
        } else {
            keyword.set(keywordAndPage.keyword);
        }
    }

    public void emitSearchEvent(View _) {
        searchKeywordEvent.setValue(keyword.get());
    }

    private void initKeywordAndPage() {
        SearchTargetUtil.setKeywordAndPageAsync(realm, DEFAULT_KEYWORD, DEFAULT_PAGE, () -> {
            keyword.set(DEFAULT_KEYWORD);
            searchKeywordEvent.setValue(keyword.get());
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        realm.close();
    }
}
