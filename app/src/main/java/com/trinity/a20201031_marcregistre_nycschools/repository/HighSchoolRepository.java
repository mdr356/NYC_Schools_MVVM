package com.trinity.a20201031_marcregistre_nycschools.repository;

import androidx.lifecycle.MutableLiveData;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import java.util.List;
import javax.inject.Singleton;

@Singleton public
interface HighSchoolRepository {
    MutableLiveData<List<NycHighSchool>> highSchoolData(RetrofitApi service);
}

