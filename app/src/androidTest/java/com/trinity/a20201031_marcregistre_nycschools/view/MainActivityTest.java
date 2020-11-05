package com.trinity.a20201031_marcregistre_nycschools.view;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.trinity.a20201031_marcregistre_nycschools.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    ActivityScenario scenario;

    @Before
    public void setup(){
        scenario = ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void loadingIndicatorIsVisibile() {
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.RESUMED);
        onView(withId(R.id.loadingIndicator)).check(matches(isDisplayed()));
    }
}
