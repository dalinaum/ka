package com.kakaobank.assignment.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakaobank.assignment.entity.SearchTarget;
import com.kakaobank.assignment.entity.util.DocumentUtil;
import com.kakaobank.assignment.entity.util.SearchTargetUtil;
import com.kakaobank.assignment.service.ImageSearchService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ImagesFragment extends AbstractImagesFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        setDocuments(DocumentUtil.getAllDocumentsAsync(getRealm()));
        setupInfiniteScroll();
        return rootView;
    }

    private void setupInfiniteScroll() {
        InfiniteScrollListener listener = new InfiniteScrollListener((GridLayoutManager) getList().getLayoutManager(), (unused) -> {
            SearchTargetUtil.getKeywordAndPageFlowable(getRealm())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(searchTargets -> {
                        if (searchTargets.size() == 1) {
                            SearchTarget searchTarget = searchTargets.first();
                            ImageSearchService.startService(getActivity(), searchTarget.keyword, searchTarget.page + 1);
                        }
                    });
        });
        getRealm().addChangeListener(realm -> {
            listener.setLoadingCompleted();
        });
        getList().addOnScrollListener(listener);
    }
}
