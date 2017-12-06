package com.kakaobank.assignment.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakaobank.assignment.entity.Document;
import com.kakaobank.assignment.entity.SearchTarget;
import com.kakaobank.assignment.entity.util.DocumentUtil;
import com.kakaobank.assignment.entity.util.SearchTargetUtil;
import com.kakaobank.assignment.service.ImageSearchService;

import io.realm.OrderedCollectionChangeSet;
import io.realm.RealmResults;

public class ImagesFragment extends AbstractImagesFragment {

    private static final String TAG = "ImagesFragment";
    private RealmResults<Document> documents;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        documents = DocumentUtil.getAllDocumentsAsync(getRealm());
        setDocuments(documents);
        setupInfiniteScroll();
        return rootView;
    }

    private void setupInfiniteScroll() {
        InfiniteScrollListener listener = new InfiniteScrollListener((GridLayoutManager) getList().getLayoutManager(), (unused) -> {
            SearchTarget keywordAndPage = SearchTargetUtil.getKeywordAndPage(getRealm());
            if (keywordAndPage != null) {
                ImageSearchService.startService(getActivity(), keywordAndPage.keyword, keywordAndPage.page + 1);
            }
        });
        documents.addChangeListener((documents, changeSet) -> {
            if (changeSet == null) {
                return;
            }
            OrderedCollectionChangeSet.Range[] insertionRanges = changeSet.getInsertionRanges();
            if (insertionRanges != null && insertionRanges.length > 0) {
                listener.setLoadingCompleted();
            }
        });
        getList().addOnScrollListener(listener);
    }
}
