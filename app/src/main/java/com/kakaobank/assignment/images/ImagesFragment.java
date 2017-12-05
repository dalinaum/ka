package com.kakaobank.assignment.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakaobank.assignment.entity.util.DocumentUtil;

public class ImagesFragment extends AbstractImagesFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        setDocuments(DocumentUtil.getAllDocumentsAsync(getRealm()));
        return rootView;
    }
}
