package com.lee.callcenter;

import org.junit.Test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class CallTest {

    @Test(expected = IllegalArgumentException.class)
    public void when_createCallWithIllegalParameter_Then_throwIllegalArgsException() {
        new Call(-1);
    }

    @Test(expected = NullPointerException.class)
    public void when_createCallWithNullParameter_Then_throwIllegalArgsException() {
        new Call(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_constructRandomCallWithIllegalFirstParameter_Then_throwIllegalArgsException() {
        Call.constructRandomCall(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_constructRandomCallWithIllegalSecondParameter_Then_throwIllegalArgsException() {
        Call.constructRandomCall(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_constructRandomCallWithMinimumTimeGreaterThanMaximumTime_Then_throwIllegalArgsException() {
        Call.constructRandomCall(2, 1);
    }

    @Test
    public void when_constructRandomCallWithParameters_Then_returnCallOFRandomLength() {
        Integer min = 5;
        Integer max = 10;
        Call call = Call.constructRandomCall(min, max);

        assertNotNull(call);
        assertTrue(min <= call.getDurationInSeconds());
        assertTrue(call.getDurationInSeconds() <= max);
    }


}