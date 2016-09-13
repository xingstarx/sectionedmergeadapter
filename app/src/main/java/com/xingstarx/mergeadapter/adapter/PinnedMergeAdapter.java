package com.xingstarx.mergeadapter.adapter;


import android.widget.ListAdapter;

import com.xingstarx.mergeadapter.widget.PinnedSectionListView;


public class PinnedMergeAdapter extends MergeAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
    public static final String TAG = "PinnedMergeAdapter";

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return false;
    }

    @Override
    public boolean isItemPinned(int position) {
        for (ListAdapter piece : getPieces()) {
            int size = piece.getCount();
            if (!(piece instanceof PinnedSectionListView.PinnedSectionListAdapter)) {
                throw new IllegalStateException("the ListAdapter piece need implements PinnedSectionListView.PinnedSectionListAdapter.");
            }
            PinnedSectionListView.PinnedSectionListAdapter pinnedSectionListAdapter = (PinnedSectionListView.PinnedSectionListAdapter) piece;
            if (position < size) {
                return pinnedSectionListAdapter.isItemPinned(position);
            }
            position -= size;
        }

        return false;
    }


}
