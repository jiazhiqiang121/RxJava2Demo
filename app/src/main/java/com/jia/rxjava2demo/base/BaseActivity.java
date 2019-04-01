package com.jia.rxjava2demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tonyjia on 2019/2/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

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
