package com.captechventures.techchallenge1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.captechventures.techchallenge1.EspressoTestsMatchers.withDrawable;
import static org.junit.Assert.assertEquals;

/**
 * Created by mluansing on 1/22/18.
 *
 * Rely on components of Android framework and require emulator to run
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testUseAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.captechventures.techchallenge1", appContext.getPackageName());
    }

    @Test
    public void testToolbarTitleTextCorrect() {
        // TODO: how to grab the toolbar / label
    }

    @Test
    public void testGreetingTextCorrect() {
        onView(withId(R.id.greeting))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.greeting)));
    }

    @Test
    public void testButtonCorrect() {
        onView(withId(R.id.button))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.hello)));
    }

    @Test
    public void testImageCorrect() {
        onView(withId(R.id.corgi_wave))
                .check(matches(isDisplayed()))
                .check(matches(withDrawable(R.drawable.corgi_wave)));
    }

    @Test
    public void testButtonClickIntentCorrect() {
        testGreetingTextCorrect();
        testButtonCorrect();
        testImageCorrect();
        testListViewHeaderDoesNotExist();
        testListViewDoesNotExist();

        onView(withId(R.id.button))
                .perform(click());

        // TODO: validate intent
//        intended(hasComponent(DisplayAppRunTimesActivity.class));
        DisplayAppRunTimesActivityInstrumentedTest test = new DisplayAppRunTimesActivityInstrumentedTest();
        test.testGreetingTextDoesNotExist();
        test.testButtonDoesNotExist();
        test.testImageDoesNotExist();
        test.testListViewHeaderCorrect();
        test.testListViewVisibleAndHasNonNullItems();
    }

    @Test
    public void testListViewHeaderDoesNotExist() {
        onView(withId(R.id.date_header))
                .check(doesNotExist());
    }

    @Test
    public void testListViewDoesNotExist() {
        onView(withId(R.id.listview))
                .check(doesNotExist());
    }

    /**
     * MainApplication (???)
     * - timestamp grab updates dateList
     * - timestamp grab updates sharedPreferences
     *
     * MainActivity
     * - TODO: toolbar title value + visible
     * - textview greeting value + visible
     * - TODO: button text + visible + clickable
     * - image visible
     * - button click --> DisplayAppRunTimesActivity
     * - TODO: all the above in landscape as well
     */

}