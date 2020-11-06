package com.trinity.a20201031_marcregistre_nycschools.viewmodel;

import android.content.Context;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import com.trinity.a20201031_marcregistre_nycschools.repository.SatRepository;
import javax.inject.Inject;

public class SatScoresViewModel extends ViewModel {

    @Inject
    public SatScoresViewModel() {}

    @Inject
    public SatRepository repository;


    public       MutableLiveData<Boolean>   isLoading   = new MutableLiveData<Boolean>();
    public final MutableLiveData<SatScores> satScores   = new MutableLiveData<>();
    public       MutableLiveData<Boolean>   showLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> hideLoading = new MutableLiveData<>();
    public MutableLiveData<SatScores> showSatView = new MutableLiveData<>();
    public MutableLiveData<Boolean> initializeSatView = new MutableLiveData<>();

    public LiveData<SatScores> getSatScoresData(Context ctx, RetrofitApi service, String schoolName) {
        repository.highSchoolSatScores(service, schoolName).observe((LifecycleOwner) ctx, schools -> {
            isLoading.postValue(false);
                satScores.postValue(schools);
        });
        return satScores;
    }

    public void showLoadingView(final Boolean shown) {
        if(shown) {
            showLoading.postValue(true);
        } else {
            hideLoading.postValue(false);
        }
    }

    public void showSatScoreOrNot(final SatScores satScores) {
        if (satScores == null) {
            initializeSatView.postValue(true);
        } else {
            showSatView.postValue(satScores);
        }
    }
}
