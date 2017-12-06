package com.kakaobank.assignment.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakaobank.assignment.R;
import com.kakaobank.assignment.entity.Document;
import com.kakaobank.assignment.entity.util.RealmUtil;

import io.realm.Realm;
import io.realm.RealmResults;

public abstract class AbstractImagesFragment extends Fragment {
    private RecyclerView list;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.images_fragment, container, false);
        list = rootView.findViewById(R.id.list);
        realm = RealmUtil.getRealmInstance();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    public Realm getRealm() {
        return realm;
    }

    public void setDocuments(RealmResults<Document> documents) {
        list.setAdapter(new ImageAdapter(documents));
    }

    public RecyclerView getList() {
        return list;
    }
}
