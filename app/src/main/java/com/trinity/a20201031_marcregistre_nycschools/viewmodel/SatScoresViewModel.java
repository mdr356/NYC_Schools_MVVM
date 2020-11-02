package com.trinity.a20201031_marcregistre_nycschools.viewmodel;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import com.trinity.a20201031_marcregistre_nycschools.repository.SatRepository;
import javax.inject.Inject;

public class SatScoresViewModel extends ViewModel {

    @Inject SatScoresViewModel() {}

    @Inject
    SatRepository repository;

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> showErrorDialog = new MutableLiveData<>();
    private final MutableLiveData<SatScores> satScores = new MutableLiveData<>();

    public LiveData<SatScores> getSatScoresData(Context ctx, RetrofitApi service, String schoolDbn) {
        repository.highSchoolSatScores(service, schoolDbn).observe((LifecycleOwner) ctx, schools -> {
            isLoading.postValue(false);
                showErrorDialog.postValue(true);
                satScores.postValue(schools);
        });
        return satScores;
    }
}
