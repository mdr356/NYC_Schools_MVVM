package com.trinity.a20201031_marcregistre_nycschools.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class SatScoresViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    SatScoresViewModel  viewModel;

    @Before
    public void setup() {
        viewModel = new SatScoresViewModel();
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

    //Add More test cases
}
