package com.trinity.a20201031_marcregistre_nycschools.dagger.module;

import com.trinity.a20201031_marcregistre_nycschools.view.HighSchoolFragment;
import com.trinity.a20201031_marcregistre_nycschools.view.SatScoresFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract HighSchoolFragment bindHighSchoolFragment();

    @ContributesAndroidInjector
    abstract SatScoresFragment bindSatScoresFragment();
}
