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

    protected HashMap<Integer, Section> sections = new HashMap<>();

    public void addSection(Section section) {
        addAdapter(section);
    }

    public void addSection(int sectionId, Section section) {
        this.sections.put(sectionId, section);
        addSection(section);
    }

    public Section getSection(int sectionId) {
        return this.sections.get(sectionId);
    }

    public static class Section extends BaseAdapter {

        private static final int ITEM_TYPE_COUNT = 2;
        private static final int ITEM_DATA = 0;
        private static final int ITEM_HEADER = 1;
        private View headerView;
        private List<String> mDatas = new ArrayList<>();
        private Context mContext;

        public Section(View headerView) {
            this.headerView = headerView;
        }

        public Section(Context mContext, View headerView, List<String> mDatas) {
            this.mContext = mContext;
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
            }
        }

        @Override
        public boolean isEnabled(int position) {
            if (position == 0)
                return false;
            else
                return true;
        }
    }

}
