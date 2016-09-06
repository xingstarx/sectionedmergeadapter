package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.ListHeaderBaseAdapter;
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
        HeaderAdapter adapter1 = new HeaderAdapter(this, new ArrayList<>(array.subList(0, 8)), R.layout.item_list, R.layout.item_header);
        HeaderAdapter adapter2 = new HeaderAdapter(this, new ArrayList<>(array.subList(8, 16)), R.layout.item_list, R.layout.item_header);
        HeaderAdapter adapter3 = new HeaderAdapter(this, new ArrayList<>(array.subList(16, 30)), R.layout.item_list, R.layout.item_header);
        adapter1.setHeaderType(1);
        adapter2.setHeaderType(2);
        adapter3.setHeaderType(3);
        final MergeAdapter adapter = new MergeAdapter();
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

    class HeaderAdapter extends ListHeaderBaseAdapter<String> implements AdapterView.OnItemClickListener {
        public int headerType;

        public void setHeaderType(int headerType) {
            this.headerType = headerType;
        }

        public HeaderAdapter(Context ctx, ArrayList<String> dataList, int theRowResourceId) {
            super(ctx, dataList, theRowResourceId);
        }

        public HeaderAdapter(Context ctx, ArrayList<String> dataList, int theRowResourceId, int headerViewResourceId) {
            super(ctx, dataList, theRowResourceId, headerViewResourceId);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void prepareViewForDisplay(View view, final String dataItem) {
            if (dataItem == null) {
                renderHeader(view, headerType);
            } else {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(dataItem);
            }

        }

        private void renderHeader(View view, int headerType) {
            TextView textView = (TextView) view.findViewById(R.id.header_text);
            switch (headerType) {
                case 1:
                    textView.setText("Header 1");
                    break;
                case 2:
                    textView.setText("Header 2");
                    break;
                case 3:
                    textView.setText("Header 3");
                    break;
                default:
                    textView.setText("Header 1");
                    break;
            }
        }

    }
}
