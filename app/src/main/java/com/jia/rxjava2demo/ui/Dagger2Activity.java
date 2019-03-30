package com.jia.rxjava2demo.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jia.rxjava2demo.R;
import com.jia.rxjava2demo.base.BaseActivity;

import javax.inject.Inject;

public class Dagger2Activity extends BaseActivity implements Dagger2Contract.View {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, Dagger2Activity.class);
        context.startActivity(intent);
    }

    @Inject
    Dagger2Presenter mPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_dagger2;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initData() {
        mPresenter.attachView(this);
        mPresenter.getDaggerMessage();
    }

    @Override
    public void configViews() {

    }

    @Override
    public void showDaggerMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}
