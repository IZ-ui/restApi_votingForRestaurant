package com.restapi.voting.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.restapi.voting.RestaurantTestData.LIST_ALL_RESTAURANT;
import static com.restapi.voting.RestaurantTestData.RESTAURANT_MATCHER;
import static com.restapi.voting.TestUtil.userHttpBasic;
import static com.restapi.voting.UserTestData.ADMIN_USER;

public class RestaurantControllerTest extends AbstractTestController {
    private static final String REST_URL = RestaurantController.REST_URL + "/";

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN_USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(LIST_ALL_RESTAURANT));
    }

}
