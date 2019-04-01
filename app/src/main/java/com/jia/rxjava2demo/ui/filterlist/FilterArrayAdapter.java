package com.jia.rxjava2demo.ui.filterlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.jia.rxjava2demo.R;

import java.util.ArrayList;
import java.util.List;

public class FilterArrayAdapter<T> extends BaseAdapter implements Filterable {

    private LayoutInflater mInflater;
    private List<T> mObjects;

    public FilterArrayAdapter(Context context) {
        mObjects = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void addAll(List<T> objects) {
        mObjects.clear();
        mObjects.addAll(objects);
    }

    @Override
    public int getCount() {
        return mObjects == null ? 0 : mObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.view_filter_item, viewGroup, false);
            holder = new ViewHolder();
            holder.mFilterItemNameTv = view.findViewById(R.id.filter_item_name_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mFilterItemNameTv.setText(mObjects.get(i).toString());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (mArrayFilter == null) {
            mArrayFilter = new ArrayFilter<>(this, mObjects);
        }
        return mArrayFilter;
    }

    static class ViewHolder {
        TextView mFilterItemNameTv;
    }

    private ArrayFilter mArrayFilter;

    private static class ArrayFilter<T> extends Filter {

        private FilterArrayAdapter mAdapter;
        private List<T> mFilterObjects;
        private List<T> mOriginalValues;
        private final Object mLock = new Object();

        private ArrayFilter(FilterArrayAdapter adapter, List<T> objects) {
            this.mAdapter = adapter;
            this.mFilterObjects = objects;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<>(mFilterObjects);
                }
            }
            //设置原来数据
            if (charSequence == null || charSequence.length() == 0) {
                List<T> list;
                synchronized (mLock) {
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();
            } else {
                //设置过滤后的数据
                String result = charSequence.toString().toLowerCase();
                ArrayList<T> values;
                synchronized (mLock) {
                    values = new ArrayList<>(mOriginalValues);
                }
                int count = values.size();
                List<T> newResultList = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    T value = values.get(i);
                    String valueText = value.toString().toLowerCase();
                    if (valueText.startsWith(result)) {
                        newResultList.add(value);
                    } else {
                        String[] words = valueText.split(" ");
                        int wordCount = words.length;

                        for (int j = 0; j < wordCount; j++) {
                            if (words[j].startsWith(result)) {
                                newResultList.add(value);
                                break;
                            }
                        }

                    }
                }
                results.values = newResultList;
                results.count = newResultList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mAdapter.mObjects = (List<T>) filterResults.values;
            if (filterResults.count > 0) {
                mAdapter.notifyDataSetChanged();
            } else {
                mAdapter.notifyDataSetInvalidated();
            }
        }
    }
}
