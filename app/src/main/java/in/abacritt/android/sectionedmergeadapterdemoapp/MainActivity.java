package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.HeaderAdapter;
import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.MergeAdapter;


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
        final MergeAdapter adapter = new MergeAdapter();
        adapter.addAdapter(new HeaderAdapter(view1, adapter1));
        adapter.addAdapter(new HeaderAdapter(view2, adapter2));
        adapter.addAdapter(new HeaderAdapter(view3, adapter3));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapter.onItemClick(adapterView, view, position, id);
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
            Toast.makeText(mContext, "position == " + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pinned_listview:
                PinnedListViewActivity.startPinnedActivity(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
