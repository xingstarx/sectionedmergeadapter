package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.MergeAdapter;
import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.PinnedMergeAdapter;
import in.abacritt.android.sectionedmergeadapterdemoapp.widget.PinnedSectionListView;


public class PinnedListViewActivity extends AppCompatActivity {

    private String TAG = "PinnedListViewActivity";

    public static void startPinnedActivity(Context context) {
        Intent intent = new Intent(context, PinnedListViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned_list_view);

        ListView listView = (ListView) findViewById(android.R.id.list);

        List<String> array = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            array.add("Row " + i);
        }
        final MergeAdapter adapter = new PinnedMergeAdapter();

        MyAdapter adapter1 = new MyAdapter(this, array.subList(0, 13), 1);
        MyAdapter adapter2 = new MyAdapter(this, array.subList(14, 29), 2);
        MyAdapter adapter3 = new MyAdapter(this, array.subList(30, 50), 3);

        adapter.addAdapter(adapter1);
        adapter.addAdapter(adapter2);
        adapter.addAdapter(adapter3);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapter.onItemClick(adapterView, view, position, id);
            }
        });
    }

    public void renderHeaderView(View view, int type) {
        TextView tv = (TextView) view.findViewById(R.id.header_text);
        switch (type) {
            case 1:
                tv.setText("Header 1");
                break;
            case 2:
                tv.setText("Header 2");
                break;
            case 3:
                tv.setText("Header 3");
                break;
        }
    }

    class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener, PinnedSectionListView.PinnedSectionListAdapter {
        private static final int ITEM_TYPE_COUNT = 2;
        private static final int ITEM_MESSAGE = 0;
        private static final int ITEM_HEADER = 1;
        private List<String> mDatas = new ArrayList<>();
        private Context mContext;
        private int mHeaderType;

        public MyAdapter(Context mContext, List<String> mDatas, int headerType) {
            this.mDatas = mDatas;
            this.mContext = mContext;
            this.mHeaderType = headerType;
        }

        @Override
        public long getItemId(int position) {
            return position;
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
            return ITEM_MESSAGE;
        }

        @Override
        public int getCount() {
            return mDatas.size() == 0 ? 0 : mDatas.size() + 1;
        }

        @Override
        public String getItem(int position) {
            if (position == 0) {
                return null;
            } else {
                return mDatas.get(position - 1);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == ITEM_HEADER) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_header, null, false);
                }
                renderHeaderView(convertView, mHeaderType);
                return convertView;
            }
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(getItem(position));
            return convertView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (getItemViewType(position) == ITEM_HEADER) {
                return;
            }
            Toast.makeText(mContext, "position == " + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return false;
        }

        @Override
        public boolean isItemPinned(int position) {
            return getItemViewType(position) == ITEM_HEADER;
        }
    }
}