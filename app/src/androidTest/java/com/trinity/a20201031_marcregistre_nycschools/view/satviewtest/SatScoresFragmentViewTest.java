package com.trinity.a20201031_marcregistre_nycschools.view.satviewtest;

import androidx.lifecycle.MutableLiveData;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.fakeretrofit.RestClient;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import com.trinity.a20201031_marcregistre_nycschools.repository.SatRepository;
import com.trinity.a20201031_marcregistre_nycschools.view.SatScoresFragment;
import com.trinity.a20201031_marcregistre_nycschools.viewmodel.SatScoresViewModel;

/*
supporting class for the test.
 */
public class SatScoresFragmentViewTest extends SatScoresFragment {

    public static SatScoresViewModel satScoresViewModelTest;
    public static RetrofitApi        retrofitApiTest;

    public MutableLiveData<SatScores> satScores = new MutableLiveData<>();

    @Override
    public void injectMembers() {
        satScoresViewModelTest = new SatScoresViewModel();
        satScoresViewModel = satScoresViewModelTest;
        retrofitApiTest = RestClient.getClient();
        request = retrofitApiTest;
        satScoresViewModel.repository = new SatRepository() {
            @Override public MutableLiveData<SatScores> highSchoolSatScores(final RetrofitApi service, final String schoolDbn) {
                return satScores;
            }
        };
    }
}
