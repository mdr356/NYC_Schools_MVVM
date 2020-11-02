package com.trinity.a20201031_marcregistre_nycschools.repository;

import androidx.lifecycle.MutableLiveData;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HighSchoolRepositoryImpl implements HighSchoolRepository {
    private final MutableLiveData<List<NycHighSchool>> highSchoolDataResponse = new MutableLiveData<List<NycHighSchool>>();

    @Override public MutableLiveData<List<NycHighSchool>> highSchoolData(final RetrofitApi service) {
        Timber.d("Api called is made to get schools data");
        Call<List<NycHighSchool>> callAsync = service.getSchoolDirectory();
        callAsync.enqueue(new Callback<List<NycHighSchool>>() {
            @Override public void onResponse(final Call<List<NycHighSchool>> call, final Response<List<NycHighSchool>> response) {
                if (response.isSuccessful()){
                    Timber.d("Api called was a success");

                    highSchoolDataResponse.postValue(response.body());
                }
            }

            @Override public void onFailure(final Call<List<NycHighSchool>> call, final Throwable t) {
                Timber.d("Api called failed.");
                highSchoolDataResponse.postValue(null);
            }
        });

        return highSchoolDataResponse;
    }
}
