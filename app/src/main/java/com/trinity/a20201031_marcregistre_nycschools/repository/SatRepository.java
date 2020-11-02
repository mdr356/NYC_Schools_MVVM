package com.trinity.a20201031_marcregistre_nycschools.repository;

import androidx.lifecycle.MutableLiveData;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import javax.inject.Singleton;

@Singleton public
interface SatRepository {
    MutableLiveData<SatScores> highSchoolSatScores(RetrofitApi service, String schoolDbn);
}

