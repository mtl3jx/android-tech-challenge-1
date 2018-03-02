package com.captechventures.techchallenge1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mluansing on 1/22/18.
 *
 * For simple, local unit tests that run on JVM
 * No Android dependencies and can run without emulator
 */

public class MainActivityUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    // TODO: does this need to be an instumented test?
    @Test
    public void testMainApplicationGrabsTimeStamp() {
        // assert list empty before hand
        assertEquals(null, MainApplication.dateList);

        // assert that after oncreate that dateList.size() == 1
        MainApplication testApp = new MainApplication();
        testApp.onCreate();
        assertEquals(1, testApp.dateList.size());
    }

}