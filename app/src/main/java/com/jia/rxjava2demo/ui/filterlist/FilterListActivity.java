package com.jia.rxjava2demo.ui.filterlist;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.jia.rxjava2demo.R;
import com.jia.rxjava2demo.base.BaseActivity;

import java.util.Arrays;

import butterknife.BindView;

/**
 * 过滤列表
 */
public class FilterListActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, FilterListActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.filter_content_et)
    EditText mFilterContentEt;
    @BindView(R.id.filter_result_lv)
    ListView mFilterResultLv;

    private FilterArrayAdapter mAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_filter_list;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initData() {
        mFilterContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        String[] dataArr = {"a", "adg", "aad", "bbb", "bds", "ddc", "赵下达", "张当时", "李东生", "李丽", "商汤"};
        mAdapter = new FilterArrayAdapter(this);
        mAdapter.addAll(Arrays.asList(dataArr));

        mFilterResultLv.setAdapter(mAdapter);
    }

    @Override
    public void configViews() {

    }

}
