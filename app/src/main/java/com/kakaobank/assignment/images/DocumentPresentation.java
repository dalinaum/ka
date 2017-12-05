package com.kakaobank.assignment.images;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class DocumentPresentation extends BaseObservable {
    @Bindable
    public String collection;

    @Bindable
    public String displaySiteName;

    @Bindable
    public String imageUrl;

    @Bindable
    public String docUrl;
}
