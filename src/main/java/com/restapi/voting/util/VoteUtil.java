package com.restapi.voting.util;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

public class VoteUtil {
    private static final LocalTime MIN_VOTE_TIME = LocalTime.MIDNIGHT;
    private static final LocalTime MAX_VOTE_TIME = LocalTime.of(11, 0);
    private static Clock CLOCK = Clock.systemDefaultZone();

    public static boolean isVotedOnTime(LocalTime time) {
        return (time.compareTo(MIN_VOTE_TIME) >= 0) && (time.compareTo(MAX_VOTE_TIME) < 0);
    }

    public static void setClock(String instantExpected) {
        CLOCK = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
    }

    public static Clock getClock() {
        return CLOCK;
    }
}