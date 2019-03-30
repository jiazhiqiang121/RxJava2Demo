package com.jia.rxjava2demo.ui;

import com.jia.rxjava2demo.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class Dagger2Module {

    @ActivityScoped
    @Binds
    abstract Dagger2Contract.Presenter taskPresenter(Dagger2Presenter presenter);
}
