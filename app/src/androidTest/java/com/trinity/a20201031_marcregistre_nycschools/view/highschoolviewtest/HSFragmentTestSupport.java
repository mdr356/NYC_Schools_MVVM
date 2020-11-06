package com.trinity.a20201031_marcregistre_nycschools.view.highschoolviewtest;

import androidx.lifecycle.MutableLiveData;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.fakeretrofit.RestClient;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepository;
import com.trinity.a20201031_marcregistre_nycschools.view.HighSchoolFragment;
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.HighSchoolViewModel;
import java.util.List;

/*
supporting class for the test.
 */
public class HSFragmentTestSupport extends HighSchoolFragment {

    public static HighSchoolViewModel highSchoolViewModelTest;
    public static RetrofitApi         retrofitApiTest;

    public MutableLiveData<List<NycHighSchool>> nycHighSchoolList   = new MutableLiveData<>();

    @Override
    public void injectMembers() {
        highSchoolViewModelTest = new HighSchoolViewModel();
        highSchoolViewModel = highSchoolViewModelTest;
        retrofitApiTest = RestClient.getClient();
        request = retrofitApiTest;
        highSchoolViewModel.highSchoolRepository = new HighSchoolRepository() {
            @Override public MutableLiveData<List<NycHighSchool>> highSchoolData(final RetrofitApi service) {
                return nycHighSchoolList;
            }
        };
    }
}
