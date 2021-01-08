package com.restapi.voting;

import com.restapi.voting.model.Restaurant;

import java.util.List;

import static com.restapi.voting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class);

    public static final int NOT_FOUND = 10;
    public static final int RESTAURANT_ID = START_SEQ + 2;

    public final static Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID + 1, "KFC");
    public final static Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 2, "McDonalds");
    public final static Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 3, "Burger King");

    public final static List<Restaurant> LIST_ALL_RESTAURANT = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID + 1, "Updated restaurant name");
    }

}
