package com.captechventures.techchallenge1;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

/**
 * Created by mluansing on 1/22/18.
 *
 * Rely on components of Android framework and require emulator to run
 */

@RunWith(AndroidJUnit4.class)
public class DisplayAppRunTimesActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void init() {
        onView(withId(R.id.button))
                .perform(click());
    }

    @Rule
    public ActivityTestRule<DisplayAppRunTimesActivity> mDisplayActivityRule = new ActivityTestRule<>(
            DisplayAppRunTimesActivity.class, false, false);

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
    public void testGreetingTextDoesNotExist() {
        onView(withId(R.id.greeting))
                .check(doesNotExist());
    }

    @Test
    public void testButtonDoesNotExist() {
        onView(withId(R.id.button))
                .check(doesNotExist());
    }

    @Test
    public void testImageDoesNotExist() {
        onView(withId(R.id.corgi_wave))
                .check(doesNotExist());
    }

    @Test
    public void testListViewHeaderCorrect() {
        onView(withId(R.id.date_header))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.run_time)));
    }

    @Test
    public void testListViewVisibleAndHasNonNullItems() {
        onView(withId(R.id.listview))
                .check(matches(isDisplayed()))
                .check((ViewAssertions.matches(EspressoTestsMatchers.withMinListSize(1))))
                .check(matches(hasDescendant(not(withText(R.string.empty_string)))));
    }

    @Test
    public void testListSizeDoesNotChange() {
        testListViewVisibleAndHasNonNullItems();

        pressBack();
        onView(withId(R.id.button))
                .perform(click());

        testListViewVisibleAndHasNonNullItems();
    }

//    @Test
//    public void testListSizeIncrementsWhenAppReopened() {
//        // must uninstall app from emulator before running
//        onView(withId(R.id.listview))
//                .check(matches(isDisplayed()))
//                .check((ViewAssertions.matches(EspressoTestsMatchers.withListSize(1))))
//                .check(matches(hasDescendant(not(withText(R.string.empty_string)))));
//
//        pressBack();
//        pressBack();
//
//        mActivityRule.launchActivity(null);
//
//        onView(withId(R.id.button))
//                .perform(click());
//
//        onView(withId(R.id.listview))
//                .check(matches(isDisplayed()))
//                .check((ViewAssertions.matches(EspressoTestsMatchers.withListSize(2))))
//                .check(matches(hasDescendant(not(withText(R.string.empty_string)))));
//    }

    @Test
    public void testScrollViewLayout() {
        final int NUMBER_ITEMS_TO_SCROLL = 10;
        setUp(NUMBER_ITEMS_TO_SCROLL);

        // list view must have at least 10 items
        onView(withId(R.id.listview))
                .check(matches(isDisplayed()))
                .check((ViewAssertions.matches(EspressoTestsMatchers.withMinListSize(NUMBER_ITEMS_TO_SCROLL))));

        // TODO: figure out how to scroll listview to bottom but also select first child w/o scroll
        // scroll down to 10th item
        onView(withId(R.id.listview))
                .perform(RecyclerViewActions.scrollToPosition(NUMBER_ITEMS_TO_SCROLL));

        // item in first position should not be > 90% visible
        onData(anything()).inAdapterView(withId(R.id.listview)).atPosition(0)
                .check(matches(not(isDisplayed())));

    }

    @Test
    public void testScrollToBottomOfList() {

    }

    public void setUp(int x) {
        // put arraylist with X timestamp stringx into intent
        ArrayList<String> dateList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("E, MMM dd, yyyy HH:mm:ss z");

        for (int i = 0; i < x; i++) {
            Date current_date = new Date();
            String now = df.format(current_date);
            dateList.add(now);
        }

        Intent i = new Intent();
        i.putExtra("dateList", dateList);
        mDisplayActivityRule.launchActivity(i);
    }

    /**
     * DisplayAppRunTimesActivity
     * - TODO: toolbar title value + visible
     * - date_header value + visible
     * - listview visible + not empty
     * - click, back, click -- list size does not change
     * - TODO: with 10+ items, scrollview should activate and last item should not be visible, date_header visible
     * - TODO: scroll to bottom of list, first item not visible, date_header still visible
     * - TODO: test close app and relaunch and listview size incremented by 1 (not sure if possible?)
     */

}