package com.kakaobank.assignment.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class ImageBindingAdapter {

    @BindingAdapter({"imgUrl"})
    public static void imageLoad(ImageView imageView, String imageUrl) {
        GlideApp.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }
}
