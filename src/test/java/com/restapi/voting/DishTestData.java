package com.restapi.voting;

import com.restapi.voting.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static com.restapi.voting.RestaurantTestData.RESTAURANT_2;
import static com.restapi.voting.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "restaurantId");

    public static final int NOT_FOUND = 10;
    public static final int DISH_ID = START_SEQ + 5;
    public static final int RESTAURANT_2_ID = RESTAURANT_2.getId();

    public static final Dish DISH_1 = new Dish(DISH_ID + 1, "Longer", getBigDecimal(9900), of(2021, Month.JANUARY, 6));
    public static final Dish DISH_2 = new Dish(DISH_ID + 2, "Nestea", getBigDecimal(6900), of(2021, Month.JANUARY, 6));
    public static final Dish DISH_3 = new Dish(DISH_ID + 3, "Twister", getBigDecimal(19900), of(2021, Month.JANUARY, 6));
    public static final Dish DISH_4 = new Dish(DISH_ID + 4, "Milk Shake", getBigDecimal(9900), of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
    public static final Dish DISH_5 = new Dish(DISH_ID + 5, "Hamburger", getBigDecimal(9900), of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
    public static final Dish DISH_6 = new Dish(DISH_ID + 6, "Big Tasty", getBigDecimal(14900), of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
    public static final Dish DISH_7 = new Dish(DISH_ID + 7, "Whopper", getBigDecimal(13600), of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
    public static final Dish DISH_8 = new Dish(DISH_ID + 8, "Cezar Roll", getBigDecimal(15900), of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
    public static final Dish DISH_9 = new Dish(DISH_ID + 9, "Pepsi", getBigDecimal(8900), of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));

    public static final List<Dish> LIST_ALL_DISHES = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6, DISH_7, DISH_8, DISH_9);

    public static final List<Dish> LIST_ALL_RESTAURANT_1_DISHES = List.of(DISH_1, DISH_2, DISH_3);

    public static Dish getNew() {
        return new Dish(null, "New Dish", getBigDecimal(999), of(2020, Month.AUGUST, 1));
    }

    public static Dish getUpdated() {
        Dish updated =  new Dish(DISH_ID + 1, "New Dish", getBigDecimal(999), of(2020, Month.AUGUST, 1));
        updated.setRestaurantId(RESTAURANT_2_ID);
        return updated;
    }

    private static BigDecimal getBigDecimal(int num) {
        return BigDecimal.valueOf(num, 2);
    }

}
