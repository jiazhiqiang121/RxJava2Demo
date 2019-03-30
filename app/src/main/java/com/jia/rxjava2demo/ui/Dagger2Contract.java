package com.jia.rxjava2demo.ui;

import com.jzq.commonlibrary.base.BaseContract;

public interface Dagger2Contract {

    interface View extends BaseContract.BaseView {
        void showDaggerMsg(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getDaggerMessage();
    }
}
