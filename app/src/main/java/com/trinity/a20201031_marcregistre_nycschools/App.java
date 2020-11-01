package com.trinity.a20201031_marcregistre_nycschools;

import android.app.Application;
import com.trinity.a20201031_marcregistre_nycschools.dagger.component.AppComponent;
import com.trinity.a20201031_marcregistre_nycschools.dagger.component.DaggerAppComponent;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class App extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent mComponent = DaggerAppComponent.builder().application(this).build();
        mComponent.inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
