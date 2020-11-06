package com.trinity.a20201031_marcregistre_nycschools.view.highschoolviewtest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.gson.Gson;
import com.trinity.a20201031_marcregistre_nycschools.R;
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static com.trinity.a20201031_marcregistre_nycschools.view.MockData.nycSchoolData;
import static com.trinity.a20201031_marcregistre_nycschools.view.highschoolviewtest.HighSchoolFragmentViewTest.highSchoolViewModelTest;

@RunWith(AndroidJUnit4.class)
public class TestHighSchoolFragmentView {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule(); // allow to run on main thread

    @Before
    public void setup() {
        TestNavHostController navController = new TestNavHostController(
            ApplicationProvider.getApplicationContext());
        navController.setGraph(R.navigation.nav_graph);
        // Create a graphical FragmentScenario for the TitleScreen
        FragmentScenario<HighSchoolFragmentViewTest> fragmentScenario = FragmentScenario.launchInContainer(HighSchoolFragmentViewTest.class);

        // Set the NavController property on the fragment
        fragmentScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navController));

    }
    @Test
    public void loadingIndicatorDsiplayed() {
        onView(withId(R.id.loadingIndicator)).check(matches(isDisplayed()));
    }

    @Test
    public void showSchoolTest() throws Throwable {
        runOnUiThread(() -> {
            NycHighSchool nyc1 = new Gson().fromJson(nycSchoolData,  NycHighSchool.class);
            List<NycHighSchool> list = new ArrayList<>();
            list.add(nyc1);
            list.add(nyc1);
            list.add(nyc1);
            list.add(nyc1);
            list.add(nyc1);
            list.add(nyc1);

            highSchoolViewModelTest.nycHighSchoolList.postValue(list);
        });
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));

    }

    @Test
    public void showErrorViewTest() throws Throwable {
        runOnUiThread(() -> {
            highSchoolViewModelTest.showErrorView.postValue(true);
        });
        onView(withId(R.id.error_view)).check(matches(isDisplayed()));
    }
}
