package com.jia.rxjava2demo.ui;

import com.jia.rxjava2demo.di.ActivityScoped;
import com.jzq.commonlibrary.base.RxPresenter;

import javax.inject.Inject;

@ActivityScoped
public class Dagger2Presenter extends RxPresenter<Dagger2Contract.View> implements
        Dagger2Contract.Presenter {

    @Inject
    public Dagger2Presenter() {

    }

    @Override
    public void getDaggerMessage() {
        mView.showDaggerMsg("获取dagger消息");
    }
}
