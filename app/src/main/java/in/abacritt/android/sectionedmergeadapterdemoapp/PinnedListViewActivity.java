package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        View view1 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv1 = (TextView) view1.findViewById(R.id.headerText);
        tv1.setText("Header 1");
        View view2 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv2 = (TextView) view2.findViewById(R.id.headerText);
        tv2.setText("Header 2");
        View view3 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv3 = (TextView) view3.findViewById(R.id.headerText);
        tv3.setText("Header 3");
        final MergeAdapter adapter = new PinnedMergeAdapter();

        MyAdapter adapter1 = new MyAdapter(this, array.subList(0, 50), view1);
        MyAdapter adapter2 = new MyAdapter(this, array.subList(51, 100), view2);
//        MyAdapter adapter3 = new MyAdapter(this, array.subList(16, 30), view3);

        adapter.addAdapter(adapter1);
        adapter.addAdapter(adapter2);
//        adapter.addAdapter(adapter3);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapter.onItemClick(adapterView, view, position, id);
            }
        });
    }

    class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener, PinnedSectionListView.PinnedSectionListAdapter {
        private static final int ITEM_TYPE_COUNT = 2;
        private static final int ITEM_MESSAGE = 0;
        private static final int ITEM_OTHER = 1;
        private List<String> mDatas = new ArrayList<>();
        private Context mContext;
        private View mHeaderView;

        public MyAdapter(Context mContext, View mHeaderView) {
            this.mContext = mContext;
            this.mHeaderView = mHeaderView;
        }

        public MyAdapter(Context mContext, List<String> mDatas, View mHeaderView) {
            this.mDatas = mDatas;
            this.mContext = mContext;
            this.mHeaderView = mHeaderView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getItemViewType(position) == ITEM_OTHER) {
                if (convertView == null) {
                    convertView =  getLayoutInflater().inflate(R.layout.item_list, parent, false);
                }
                TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                textView.setText(getItem(position));
            } else {
                if (convertView == null) {
                    convertView =  getLayoutInflater().inflate(R.layout.item_list_2, parent, false);
                }
                TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
                textView.setText(getItem(position));
            }
            return convertView;
        }

        @Override
        public int getViewTypeCount() {
            return ITEM_TYPE_COUNT;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 15 == 0) {
                return ITEM_OTHER;
            }
            return ITEM_MESSAGE;
        }

        @Override
        public int getCount() {
            return mDatas.size() == 0 ? 0 : mDatas.size();
        }

        @Override
        public String getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(mContext, "Hello World", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return false;
        }

        @Override
        public boolean isItemPinned(int position) {
            return getItemViewType(position) == ITEM_OTHER;
        }
    }
}