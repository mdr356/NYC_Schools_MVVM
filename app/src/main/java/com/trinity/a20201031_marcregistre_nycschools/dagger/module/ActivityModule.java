package com.trinity.a20201031_marcregistre_nycschools.dagger.module;

import com.trinity.a20201031_marcregistre_nycschools.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
