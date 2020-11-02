package com.trinity.a20201031_marcregistre_nycschools.repository;

import androidx.lifecycle.MutableLiveData;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SatRepositoryImpl implements SatRepository {

    @Override public MutableLiveData<SatScores> highSchoolSatScores(final RetrofitApi service, final String schoolDbn) {
        Timber.d("Api call to get school SAT scores");
        final MutableLiveData<SatScores> highSchoolDataResponse = new MutableLiveData<>();

        Call<SatScores> callAsync = service.getSchoolSatScores(schoolDbn);
        callAsync.enqueue(new Callback<SatScores>() {
            @Override public void onResponse(final Call<SatScores> call, final Response<SatScores> response) {
                if (response.isSuccessful()){
                    Timber.d("API call was a success");
                    highSchoolDataResponse.postValue(response.body());
                }
            }

            @Override public void onFailure(final Call<SatScores> call, final Throwable t) {
                Timber.d("API called failed.");
                highSchoolDataResponse.postValue(null);
            }
        });

        return highSchoolDataResponse;
    }
}
