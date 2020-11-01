package com.trinity.a20201031_marcregistre_nycschools.dagger.component;

import android.app.Application;

import com.trinity.a20201031_marcregistre_nycschools.App;
import com.trinity.a20201031_marcregistre_nycschools.dagger.module.ActivityModule;
import com.trinity.a20201031_marcregistre_nycschools.dagger.module.AppModule;
import com.trinity.a20201031_marcregistre_nycschools.dagger.module.FragmentModule;
import com.trinity.a20201031_marcregistre_nycschools.dagger.module.RetrofitModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, // Important
        ActivityModule.class,
        FragmentModule.class,
        RetrofitModule.class,
        AppModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
