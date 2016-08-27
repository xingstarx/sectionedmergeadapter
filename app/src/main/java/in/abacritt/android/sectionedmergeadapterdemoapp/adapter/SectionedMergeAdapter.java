package in.abacritt.android.sectionedmergeadapterdemoapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

        private static final int ITEM_TYPE_COUNT = 2;
        private static final int ITEM_DATA = 0;
        private static final int ITEM_HEADER = 1;
        private View headerView;
<<<<<<< 32294b787376643b597db4e6336044b88b941b3e
        private List<String> mDatas = new ArrayList<>();
        private Context mContext;

        public Section(View headerView) {
            this.headerView = headerView;
        }

        public Section(Context mContext, View headerView, List<String> mDatas) {
            this.mContext = mContext;
=======
        private ListAdapter adapter;
        private static final int ITEM_HEADER = 0;
        private static final int ITEM_DATA = 1;
        private static final int ITEM_TYPE_COUNT = 2;

        public HeaderAdapter(View headerView, ListAdapter adapter) {
>>>>>>> fix scroll to bottom crash bug
            this.headerView = headerView;
            this.mDatas = mDatas;
        }

        public void setmDatas(List<String> datas) {
            this.mDatas = datas;
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
            return mDatas.size() == 0 ? mDatas.size() : mDatas.size() + 1;
        }

        @Override
<<<<<<< 32294b787376643b597db4e6336044b88b941b3e
        public String getItem(int i) {
            if (i == 0)
                return null;
            else
                return mDatas.get(i - 1);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == ITEM_HEADER) {
                return headerView;
            } else {
                if (convertView == null) {
//                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
                }
                String data = getItem(position);
                TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                textView.setText(data);
                return convertView;
=======
        public Object getItem(int position) {
            if (position == 0)
                return null;
            else
                return adapter.getItem(position - 1);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return ITEM_HEADER;
            }
            return ITEM_DATA;
        }

        @Override
        public int getViewTypeCount() {
            return ITEM_TYPE_COUNT;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (getItemViewType(position) == ITEM_HEADER) {
                return headerView;
            } else {
                return adapter.getView(position - 1, view, viewGroup);
>>>>>>> fix scroll to bottom crash bug
            }
        }

        @Override
        public boolean isEnabled(int position) {
            if (getItemViewType(position) == 0)
                return false;
<<<<<<< 32294b787376643b597db4e6336044b88b941b3e
            else
                return true;
=======
            else {
                return adapter.isEnabled(position - 1);
            }
>>>>>>> fix scroll to bottom crash bug
        }
    }

}
