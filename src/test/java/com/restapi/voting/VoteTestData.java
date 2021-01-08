package com.restapi.voting;

import com.restapi.voting.model.Vote;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.of;
import static com.restapi.voting.RestaurantTestData.RESTAURANT_1;
import static com.restapi.voting.UserTestData.USER_1;
import static com.restapi.voting.UserTestData.USER_2;
import static com.restapi.voting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Vote.class, "");

    public static final int VOTE_ID = START_SEQ + 14;
    public static final int RESTAURANT_1_ID = RESTAURANT_1.getId();

    public static final Vote VOTE_1 = new Vote(VOTE_ID + 1, USER_1, RESTAURANT_1_ID, of(2021, Month.JANUARY, 06));

    public final static List<Vote> LIST_ALL_VOTE = List.of(VOTE_1);

    public static Vote getNew() {
        return new Vote(USER_2, RESTAURANT_1_ID, LocalDate.now());
    }

    public static Vote getNewWithId() {
        return new Vote(VOTE_ID + 2, USER_2, RESTAURANT_1_ID, LocalDate.now());
    }

}
