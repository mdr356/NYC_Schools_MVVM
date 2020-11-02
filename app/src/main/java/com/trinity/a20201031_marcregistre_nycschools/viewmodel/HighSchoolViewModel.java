package com.trinity.a20201031_marcregistre_nycschools.viewmodel;

import android.content.Context;
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
    HighSchoolRepository highSchoolRepository;

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> showErrorDialog = new MutableLiveData<>();
    private final MutableLiveData<List<NycHighSchool>> nycHighSchool = new MutableLiveData<>();

    public LiveData<List<NycHighSchool>> getNycHighSchoolData(Context ctx, RetrofitApi service) {
        highSchoolRepository.highSchoolData(service).observe((LifecycleOwner) ctx, schools -> {
            isLoading.postValue(false);
            if(schools == null) {
                showErrorDialog.postValue(true);
            } else {
                nycHighSchool.postValue(schools);
            }
        });
        return nycHighSchool;

    }
}

