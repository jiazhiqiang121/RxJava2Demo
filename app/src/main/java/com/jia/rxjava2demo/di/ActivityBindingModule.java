package com.jia.rxjava2demo.di;

import com.jia.rxjava2demo.ui.Dagger2Activity;
import com.jia.rxjava2demo.ui.Dagger2Module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = Dagger2Module.class)
    abstract Dagger2Activity taskDagger2Activity();
}
