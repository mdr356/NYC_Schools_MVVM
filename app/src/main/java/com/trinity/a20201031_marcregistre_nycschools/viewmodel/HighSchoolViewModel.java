package com.trinity.a20201031_marcregistre_nycschools.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepository;
import java.util.List;
import javax.inject.Inject;

public class HighSchoolViewModel extends ViewModel {


    @Inject public HighSchoolViewModel(){}

    @Inject
    public HighSchoolRepository highSchoolRepository;

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> showErrorDialog = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> hideLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> showErrorView  = new MutableLiveData<>();

    public MutableLiveData<List<NycHighSchool>> nycHighSchoolList   = new MutableLiveData<>();

    public LiveData<List<NycHighSchool>> getNycHighSchoolData(LifecycleOwner ctx, RetrofitApi service) {
        highSchoolRepository.highSchoolData(service).observe(ctx, schools -> {
            isLoading.postValue(false);
            if(schools == null) {
                showErrorDialog.postValue(true);
            } else {
                nycHighSchoolList.postValue(schools);
            }
        });
        return nycHighSchoolList;

    }

    public void showLoadingView(final Boolean shown) {
        if(shown) {
            showLoading.postValue(true);
        } else {
            hideLoading.postValue(false);
        }
    }
}

