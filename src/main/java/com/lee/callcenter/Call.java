package com.lee.callcenter;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  Class specifies the calls placed to the call center.
 *
 *     The {@code Call} class defines the call that is coming into the call center.
 *     The fields include duration of call, and the method {@code constructRandomCall()} is
 *     used to construct a call within the system.
 *
 * @author leefowler
 */
public class Call {

    private Integer durationInSeconds;

    /**
     * Creates a new Call with duration measured in seconds
     *
     * @param durationInSeconds duration in seconds must be equal or greater than zero
     */
    public Call(Integer durationInSeconds) {
        Validate.notNull(durationInSeconds);
        Validate.isTrue(durationInSeconds >= 0);
        this.durationInSeconds = durationInSeconds;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    /**
     * Builds a new random call
     *
     * @param minDurationInSeconds minimum duration in seconds must be equal or greater than zero
     * @param maxDurationInSeconds maximun duration in seconds must be equal or greater than minDurationInSeconds
     * @return A new random call with a random duration value between minimum and maximum duration
     */
    public static Call constructRandomCall(Integer minDurationInSeconds, Integer maxDurationInSeconds) {
        Validate.isTrue(maxDurationInSeconds >= minDurationInSeconds && minDurationInSeconds >= 0);
        return new Call(ThreadLocalRandom.current().nextInt(minDurationInSeconds, maxDurationInSeconds + 1));
    }

    /**
     * Builds a new random call list
     *
     * @param listSize             amount of random calls to be created
     * @param minDurationInSeconds minimum duration in seconds of each call must be equal or greater than zero
     * @param maxDurationInSeconds maximun duration in seconds of each call must be equal or greater than minDurationInSeconds
     * @return A new list of random calls each with a random duration value between minimum and maximum duration
     */
    public static List<Call> constructListOfRandomCalls(Integer listSize, Integer minDurationInSeconds, Integer maxDurationInSeconds) {
        Validate.isTrue(listSize >= 0);
        List<Call> callList = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            callList.add(constructRandomCall(minDurationInSeconds, maxDurationInSeconds));
        }
        return callList;
    }

}