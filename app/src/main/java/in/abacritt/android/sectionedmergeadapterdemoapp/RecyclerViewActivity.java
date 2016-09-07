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


public class RecyclerViewActivity extends AppCompatActivity {

    private String TAG = "RecyclerViewActivity";
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        List<String> array = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            array.add("Row " + i);
        }
        mAdapter = new MyAdapter(array);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, null));
        mRecyclerView.setAdapter(mAdapter);
    }

    public static void startRecyclerViewActivity(Context context) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(intent);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData = new ArrayList<>();


        public MyAdapter(List<String> mData) {
            this.mData = mData;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e(TAG, "onCreateViewHolder() method invoked");
            final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
            final View sView = mInflater.inflate(R.layout.item_list, parent, false);
            return new ViewHolder(sView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.e(TAG, "onBindViewHolder() method invoked");
            holder.textView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            Log.e(TAG, "getItemCount() method invoked");
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            Log.e(TAG, "getItemViewType() method invoked");
            return super.getItemViewType(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public ViewHolder(View itemView) {
                super(itemView);
                Log.e(TAG, "ViewHolder() method invoked");
                textView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }

}
