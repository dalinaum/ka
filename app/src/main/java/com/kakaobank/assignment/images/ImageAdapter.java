package com.kakaobank.assignment.images;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kakaobank.assignment.databinding.DocumentItemBinding;
import com.kakaobank.assignment.entity.Document;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class ImageAdapter extends RealmRecyclerViewAdapter<Document, ImageAdapter.DocumentBindingHolder> {

    public ImageAdapter(OrderedRealmCollection<Document> documents) {
        super(documents, true);
    }

    @Override
    public DocumentBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DocumentItemBinding binding = DocumentItemBinding.inflate(inflater, parent, false);
        return new DocumentBindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(DocumentBindingHolder holder, int position) {
        Document item = getItem(position);
        DocumentPresentation presentation = new DocumentPresentation();
        presentation.collection = item.collection;
        presentation.displaySiteName = item.displaySiteName;
        presentation.docUrl = item.docUrl;
        presentation.imageUrl = item.imageUrl;
        holder.setPresentation(presentation);
    }

    public static class DocumentBindingHolder extends RecyclerView.ViewHolder {
        private DocumentItemBinding binding;

        public DocumentBindingHolder(DocumentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public DocumentItemBinding getBinding() {
            return binding;
        }

        public void setPresentation(DocumentPresentation presentation) {
            binding.setPresentation(presentation);
        }
    }
}
