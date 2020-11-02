package com.trinity.a20201031_marcregistre_nycschools.dagger.module;

import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepository;
import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepositoryImpl;
import com.trinity.a20201031_marcregistre_nycschools.repository.SatRepository;
import com.trinity.a20201031_marcregistre_nycschools.repository.SatRepositoryImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public HighSchoolRepository provideHighSchoolRepository()  {
        return new HighSchoolRepositoryImpl();
    }

    @Provides
    public SatRepository provideSatRepository() {
        return new SatRepositoryImpl();
    }

}
