package com.trinity.a20201031_marcregistre_nycschools.view.satviewtest;

import android.os.Bundle;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.gson.Gson;
import com.trinity.a20201031_marcregistre_nycschools.R;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static com.trinity.a20201031_marcregistre_nycschools.view.MockData.nycSchoolData;
import static com.trinity.a20201031_marcregistre_nycschools.view.MockData.satScoresData;
import static com.trinity.a20201031_marcregistre_nycschools.view.satviewtest.SSFragmentTestSupport.satScoresViewModelTest;

@RunWith(AndroidJUnit4.class)
public class TestSatScoresFragmentView {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule(); // allow to run on main thread

    @Before
    public void setup() {
        TestNavHostController navController = new TestNavHostController(
            ApplicationProvider.getApplicationContext());
        navController.setGraph(R.navigation.nav_graph);

        Bundle result = new Bundle();
        NycHighSchool nyc1 = new Gson().fromJson(nycSchoolData, NycHighSchool.class);
        result.putParcelable("SCHOOL_DATA_BUNDLE", nyc1);

        // Create a graphical FragmentScenario for the TitleScreen
        FragmentScenario<SSFragmentTestSupport> fragmentScenario = FragmentScenario.launchInContainer(SSFragmentTestSupport.class, result);

        // Set the NavController property on the fragment
        fragmentScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navController));

    }
    @Test
    public void loadingIndicatorDisplayed() {
        onView(withId(R.id.loadingIndicator)).check(matches(isDisplayed()));
    }

    @Test
    public void satAvgScoresTest() throws Throwable {
        runOnUiThread(() -> {
            SatScores satScores = new Gson().fromJson(satScoresData,  SatScores.class);
            satScoresViewModelTest.satScores.postValue(satScores);
        });
        onView(withText("404")).check(matches(isDisplayed())); // sat_math_avg_score
        onView(withText("29")).check(matches(isDisplayed())); // num_of_sat_test_takers_title
    }
}
