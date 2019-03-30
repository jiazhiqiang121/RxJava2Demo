package com.jia.rxjava2demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.DaggerActivity;

/**
 * Created by tonyjia on 2019/2/13.
 */
public abstract class BaseActivity extends DaggerActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        unbinder = ButterKnife.bind(this);

        initToolBar();
        initData();
        configViews();
    }

    public abstract int getLayoutResId();

    public abstract void initToolBar();

    public abstract void initData();

    public abstract void configViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
