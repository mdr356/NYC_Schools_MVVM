package com.trinity.a20201031_marcregistre_nycschools.viewmodel;

import android.view.View;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepository;
import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepositoryImpl;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import retrofit2.Call;
import timber.log.Timber;

import static com.nhaarman.mockitokotlin2.OngoingStubbingKt.whenever;
import static org.mockito.Mockito.when;

public class HighSchoolViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    HighSchoolViewModel  viewModel;

    @InjectMocks RetrofitApi service;
    @Mock
    LifecycleOwner lifecycleOwner;

    @Before
    public void setup() {
        viewModel = new HighSchoolViewModel();
    }

    @Test
    public void whenisLoadingIsTrueThenAssertObserverTrue() {
        viewModel.isLoading.observeForever(Assert::assertTrue);
        viewModel.isLoading.postValue(true);
    }

    @Test
    public void whenisLoadingIsFalseThenAssertObserverFalse() {
        viewModel.isLoading.observeForever(Assert::assertFalse);
        viewModel.isLoading.postValue(false);
    }

    @Test
    public void whenShowLoadingViewIsTrue_thenshowLoading_istrue_and_hideLoading_isFalse() {
        viewModel.showLoading.observeForever(Assert::assertTrue);
        viewModel.hideLoading.observeForever(Assert::assertFalse);
        viewModel.showLoading.postValue(true);
    }

    @Test
    public void whenHideLoadingViewIsFalse_thenshowLoading_isFalse_and_hideLoading_isTrue() {
        viewModel.showLoading.observeForever(Assert::assertFalse);
        viewModel.hideLoading.observeForever(Assert::assertTrue);
        viewModel.showLoading.postValue(false);
    }
}
