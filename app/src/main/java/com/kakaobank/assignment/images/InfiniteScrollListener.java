package com.kakaobank.assignment.images;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "InfiniteScrollListener";
    private final LinearLayoutManager linearLayoutManager;
    private final Loader loader;
    private int previousCount = -1;
    private boolean loading = false;
    private boolean noMoreData = false;

    public InfiniteScrollListener(LinearLayoutManager linearLayoutManager, Loader loader) {
        this.linearLayoutManager = linearLayoutManager;
        this.loader = loader;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (recyclerView == null) {
            return;
        }

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (firstVisibleItem == -1 || totalItemCount == 0 || visibleItemCount == totalItemCount) {
            return;
        }

        if (!loading && !noMoreData && firstVisibleItem + visibleItemCount >= totalItemCount) {
            loading = true;
            previousCount = totalItemCount;
            loader.load(this);
        }
    }

    public boolean isNoMoreData() {
        return noMoreData;
    }

    public void setNoMoreData(boolean noMoreData) {
        this.noMoreData = noMoreData;
    }

    public void setLoadingCompleted() {
        loading = false;
    }

    public interface Loader {
        void load(InfiniteScrollListener listener);
    }
}
