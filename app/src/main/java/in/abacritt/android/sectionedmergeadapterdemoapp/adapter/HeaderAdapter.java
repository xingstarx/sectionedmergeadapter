package in.abacritt.android.sectionedmergeadapterdemoapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;


public class HeaderAdapter extends BaseAdapter {

    private static final int ITEM_HEADER = 0;
    private static final int ITEM_DATA = 1;
    private static final int ITEM_TYPE_COUNT = 2;
    private View headerView;
    private ListAdapter adapter;

    public HeaderAdapter(View headerView, ListAdapter adapter) {
        this.headerView = headerView;
        this.adapter = adapter;
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
        return adapter.getCount() == 0 ? adapter.getCount() : adapter.getCount() + 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        if (position == 0)
            return null;
        else
            return adapter.getItem(position - 1);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (getItemViewType(position) == ITEM_HEADER) {
            return headerView;
        } else {
            return adapter.getView(position - 1, view, viewGroup);
        }
    }

    @Override
    public boolean isEnabled(int position) {
        if (getItemViewType(position) == ITEM_HEADER) {
            return false;
        } else {
            return adapter.isEnabled(position - 1);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (getItemViewType(position) == ITEM_HEADER) {
            return;
        }
        if (adapter instanceof AdapterView.OnItemClickListener) {
            ((AdapterView.OnItemClickListener) adapter).onItemClick(parent, view, position - 1, id);
        } else {
            throw new IllegalStateException("adapter must implements AdapterView.OnItemClickListener");
        }
    }
}

