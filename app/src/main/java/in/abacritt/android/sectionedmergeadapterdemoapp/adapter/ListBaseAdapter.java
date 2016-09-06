package in.abacritt.android.sectionedmergeadapterdemoapp.adapter;

/**
 * Created by xiongxingxing on 15/10/26.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;


public abstract class ListBaseAdapter<T> extends BaseAdapter {
    protected Context context;
    protected ArrayList<T> dataList;
    protected int rowViewResourceId;


    public ListBaseAdapter(Context context, ArrayList<T> dataList, int theRowResourceId) {
        this.context = context;
        this.dataList = dataList;
        this.rowViewResourceId = theRowResourceId;
    }


    abstract public void prepareViewForDisplay(View view, T dataItem);


    public int getCount() {
        return this.dataList.size();
    }


    public Object getItem(int position) {
        return this.dataList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parentView) {
        View view;
        if (convertView == null) {
            view = View.inflate(this.context, this.rowViewResourceId, null);
        } else {
            view = convertView;
        }
        // to be supplied by subclass
        T dataItem = this.dataList.get(position);
        prepareViewForDisplay(view, dataItem);
        return view;
    }


    public ArrayList<T> getDataList() {
        return dataList;
    }


    public void setDataList(ArrayList<T> dataList) {
        this.dataList = dataList;
    }


    public int getRowViewResourceId() {
        return rowViewResourceId;
    }


    public void setRowViewResourceId(int rowViewResourceId) {
        this.rowViewResourceId = rowViewResourceId;
    }


    public void add(T item) {
        dataList.add(item);
        notifyDataSetChanged();
    }


    public void add(int index, T item) {
        dataList.add(index, item);
        notifyDataSetChanged();
    }


    public void addAll(Collection<? extends T> collection) {
        dataList.addAll(collection);
        //super.addAll(collection);
    }


    public void remove(int index) {
        dataList.remove(index);
        notifyDataSetChanged();
    }


    public void remove(T item) {
        dataList.remove(item);
        notifyDataSetChanged();
    }


    public void replace(int index, T item) {
        dataList.remove(index);
        dataList.add(index, item);
    }
}
