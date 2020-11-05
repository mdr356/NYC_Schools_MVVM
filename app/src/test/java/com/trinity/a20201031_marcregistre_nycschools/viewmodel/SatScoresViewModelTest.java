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
    public void GivenisLoadingIsTrueWhenisLoadingIsbeingObserve_ThenIsLoadingShouldBeTrue() {
        viewModel.isLoading.observeForever(Assert::assertTrue);
        viewModel.isLoading.postValue(true);
    }

    @Test
    public void GivenisLoadingIsFalseWhenisLoadingIsBeingObserveThenIsLoadingShouldBeFalse() {
        viewModel.isLoading.observeForever(Assert::assertFalse);
        viewModel.isLoading.postValue(false);
    }

    @Test
    public void Given_ShowLoadingIsTrue_WhenShowLoadingIsBeingObserved_ThenShowloadingShouldBeTrue_andHideLoadingFalse() {
        viewModel.showLoading.observeForever(Assert::assertTrue);
        viewModel.hideLoading.observeForever(Assert::assertFalse);
        viewModel.showLoading.postValue(true);
    }

    @Test
    public void GivenShowLoadingIsFalse_WhenShowLoadingIsBeingObservedThenShowloadingShouldBeFalse_andHideLoadingTrue() {
        viewModel.showLoading.observeForever(Assert::assertFalse);
        viewModel.hideLoading.observeForever(Assert::assertTrue);
        viewModel.showLoading.postValue(false);
    }
}
