package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.SectionedMergeAdapter;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(android.R.id.list);

        List<String> array = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            array.add("Row " + i);
        }

        MyAdapter adapter1 = new MyAdapter(this, array.subList(0, 8));
        MyAdapter adapter2 = new MyAdapter(this, array.subList(8, 16));
        MyAdapter adapter3 = new MyAdapter(this, array.subList(16, 30));
        View view1 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv1 = (TextView) view1.findViewById(R.id.headerText);
        tv1.setText("Header 1");
        View view2 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv2 = (TextView) view2.findViewById(R.id.headerText);
        tv2.setText("Header 2");
        View view3 = getLayoutInflater().inflate(R.layout.item_header, null, false);
        TextView tv3 = (TextView) view3.findViewById(R.id.headerText);
        tv3.setText("Header 3");
        final SectionedMergeAdapter adapter = new SectionedMergeAdapter();
        adapter.addHeaderAdapter(new SectionedMergeAdapter.HeaderAdapter(view1, adapter1));
        adapter.addHeaderAdapter(new SectionedMergeAdapter.HeaderAdapter(view2, adapter2));
        adapter.addHeaderAdapter(new SectionedMergeAdapter.HeaderAdapter(view3, adapter3));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (ListAdapter piece : adapter.getPieces()) {
                    int size = piece.getCount();
                    if (position < size) {
                        SectionedMergeAdapter.HeaderAdapter headerAdapter = (SectionedMergeAdapter.HeaderAdapter) piece;
                        headerAdapter.onItemClick(adapterView, view, position, id);
                        break;
                    }
                    position -= size;
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
        private List<String> mDatas = new ArrayList<>();
        private Context mContext;

        public MyAdapter(Context mContext, List<String> mDatas) {
            this.mContext = mContext;
            this.mDatas = mDatas;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return mDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(mDatas.get(position));
            return convertView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e(TAG, "position == " + position);
        }
    }
}
