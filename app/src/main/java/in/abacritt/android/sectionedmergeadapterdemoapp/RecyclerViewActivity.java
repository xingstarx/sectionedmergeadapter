package in.abacritt.android.sectionedmergeadapterdemoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.abacritt.android.sectionedmergeadapterdemoapp.adapter.MergeRecyclerAdapter;


public class RecyclerViewActivity extends AppCompatActivity {

    private String TAG = "RecyclerViewActivity";
    private RecyclerView mRecyclerView;

    public static void startRecyclerViewActivity(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        List<String> array = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            array.add("Row " + i);
        }
        MyAdapter mAdapter = new MyAdapter(array);
        MyAdapter mAdapter2 = new MyAdapter(array);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        MergeRecyclerAdapter mMergeRecyclerAdapter = new MergeRecyclerAdapter();
        mMergeRecyclerAdapter.addAdapter(mAdapter);
        mMergeRecyclerAdapter.addAdapter(mAdapter2);
        mRecyclerView.setAdapter(mMergeRecyclerAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MergeRecyclerAdapter.OnViewTypeCheckListener {
        private static final int ITEM_HEADER = 0;
        private static final int ITEM_CONTENT = 1;
        private List<String> mData = new ArrayList<>();


        public MyAdapter(List<String> mData) {
            this.mData = mData;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e(TAG, "onCreateViewHolder() method invoked parent == " + parent + ", viewType == " + viewType);
            if (viewType == ITEM_HEADER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                return new TitleViewHolder(view);
            } else if (viewType == ITEM_CONTENT) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
                return new ContentViewHolder(view);
            }
            throw new IllegalStateException("Adapter don't have this viewType " + viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Log.e(TAG, "onBindViewHolder() method invoked, holder == " + holder + ", position ==" + position);
            if (holder instanceof ContentViewHolder) {
                ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                contentViewHolder.contentView.setText(mData.get(position));
            }
            if (holder instanceof TitleViewHolder) {
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                titleViewHolder.textView.setText(mData.get(position));
            }
        }

        @Override
        public int getItemCount() {
            Log.e(TAG, "getItemCount() method invoked");
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            Log.e(TAG, "getItemViewType() method invoked");
            if (position == 0) {
                return ITEM_HEADER;
            }
            return ITEM_CONTENT;
        }

        @Override
        public boolean checkViewType(int viewType) {
            return viewType == ITEM_HEADER || viewType == ITEM_CONTENT;
        }

        public class ContentViewHolder extends RecyclerView.ViewHolder {
            TextView contentView;

            public ContentViewHolder(View itemView) {
                super(itemView);
                Log.e(TAG, "ViewHolder() method invoked");
                contentView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

        public class TitleViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public TitleViewHolder(View itemView) {
                super(itemView);
                Log.e(TAG, "ViewHolder() method invoked");
                textView = (TextView) itemView.findViewById(R.id.header_text);
            }
        }
    }

}
