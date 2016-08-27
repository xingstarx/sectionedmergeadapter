package in.abacritt.android.sectionedmergeadapterdemoapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.HashMap;


public class SectionedMergeAdapter extends MergeAdapter {

    protected HashMap<Integer, HeaderAdapter> headerAdapterHashMap = new HashMap<>();

    public void addHeaderAdapter(HeaderAdapter headerAdapter) {
        addAdapter(headerAdapter);
    }

    public void addHeaderAdapter(int sectionId, HeaderAdapter headerAdapter) {
        this.headerAdapterHashMap.put(sectionId, headerAdapter);
        addHeaderAdapter(headerAdapter);
    }

    public HeaderAdapter getHeaderAdapter(int sectionId) {
        return this.headerAdapterHashMap.get(sectionId);
    }

    public static class HeaderAdapter extends BaseAdapter {

        private static final int ITEM_HEADER = 0;
        private static final int ITEM_DATA = 1;
        private static final int ITEM_TYPE_COUNT = 2;
        private View headerView;
        private ListAdapter adapter;

        public HeaderAdapter(View headerView, ListAdapter adapter) {
            this.headerView = headerView;
            this.adapter = adapter;
        }

        public View getHeaderView() {
            return headerView;
        }

        @Override
        public int getViewTypeCount() {
            return ITEM_TYPE_COUNT;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return ITEM_HEADER;
            }
            return ITEM_DATA;
        }

        @Override
        public int getCount() {
            return adapter.getCount() == 0 ? adapter.getCount() : adapter.getCount() + 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public Object getItem(int position) {
            if (position == 0)
                return null;
            else
                return adapter.getItem(position - 1);
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (getItemViewType(position) == ITEM_HEADER) {
                return headerView;
            } else {
                return adapter.getView(position - 1, view, viewGroup);
            }
        }

        @Override
        public boolean isEnabled(int position) {
            if (getItemViewType(position) == 0) {
                return false;
            } else {
                return adapter.isEnabled(position - 1);
            }
        }
    }

}
