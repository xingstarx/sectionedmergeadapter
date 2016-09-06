package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.ListHeaderBaseAdapter;
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

        HeaderAdapter adapter1 = new HeaderAdapter(this, new ArrayList<>(array.subList(0, 13)), R.layout.item_list, R.layout.item_header, 1);
        HeaderAdapter adapter2 = new HeaderAdapter(this, new ArrayList<>(array.subList(13, 29)), R.layout.item_list, R.layout.item_header, 2);
        HeaderAdapter adapter3 = new HeaderAdapter(this, new ArrayList<>(array.subList(29, 50)), R.layout.item_list, R.layout.item_header, 3);
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


    class HeaderAdapter extends ListHeaderBaseAdapter<String> implements AdapterView.OnItemClickListener, PinnedSectionListView.PinnedSectionListAdapter {
        private int mHeaderType;

        public void setHeaderType(int headerType) {
            this.mHeaderType = headerType;
        }

        public HeaderAdapter(Context ctx, ArrayList<String> dataList, int theRowResourceId) {
            super(ctx, dataList, theRowResourceId);
        }

        public HeaderAdapter(Context ctx, ArrayList<String> dataList, int theRowResourceId, int headerViewResourceId) {
            super(ctx, dataList, theRowResourceId, headerViewResourceId);
        }

        public HeaderAdapter(Context ctx, ArrayList<String> dataList, int theRowResourceId, int headerViewResourceId, int headerType) {
            super(ctx, dataList, theRowResourceId, headerViewResourceId);
            mHeaderType = headerType;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (getItemViewType(position) == TYPE_HEADER) {
                return;
            }
            Toast.makeText(context, "position == " + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void prepareViewForDisplay(View view, String dataItem) {
            if (dataItem == null) {
                renderHeaderView(view, mHeaderType);
            } else {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(dataItem);
            }
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return false;
        }

        @Override
        public boolean isItemPinned(int position) {
            return getItemViewType(position) == TYPE_HEADER;
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
    }
}