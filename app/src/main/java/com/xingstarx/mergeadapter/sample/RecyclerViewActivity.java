package com.xingstarx.mergeadapter.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingstarx.mergeadapter.MergeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


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
        for (int i = 0; i < 10; i++) {
            array.add("Row " + i);
        }
        final MyAdapter mAdapter = new MyAdapter(array);
        final MyAdapter mAdapter2 = new MyAdapter(new CopyOnWriteArrayList<>(array));


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        final MergeRecyclerAdapter mMergeRecyclerAdapter = new MergeRecyclerAdapter();
        mMergeRecyclerAdapter.addAdapter(mAdapter);
        mMergeRecyclerAdapter.addAdapter(mAdapter2);
        mRecyclerView.setAdapter(mMergeRecyclerAdapter);


        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addData("Android Developer xingstarx", 8);
                mMergeRecyclerAdapter.notifyItemInserted(8);

                mRecyclerView.removeCallbacks(this);
                mRecyclerView.postDelayed(this, 2000);
            }
        }, 2000);

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter2.addData("Android Developer xingstarx2", 5);
                mMergeRecyclerAdapter.notifyItemInserted(mAdapter.getItemCount() + 5);

                mRecyclerView.removeCallbacks(this);
                mRecyclerView.postDelayed(this, 3000);
            }
        }, 3000);
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MergeRecyclerAdapter.OnViewTypeCheckListener {
        private static final int ITEM_HEADER = 0;
        private static final int ITEM_CONTENT = 1;
        private List<String> mData = new ArrayList<>();


        public MyAdapter(List<String> mData) {
            this.mData = mData;
        }

        public void addData(String data) {
            mData.add(data);
        }

        public void addData(String data, int position) {
            mData.add(position, data);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
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
                contentView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }

        public class TitleViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public TitleViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.header_text);
            }
        }
    }

}
