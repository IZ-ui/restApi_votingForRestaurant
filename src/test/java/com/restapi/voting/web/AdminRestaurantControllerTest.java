package com.restapi.voting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import com.restapi.voting.model.Restaurant;
import com.restapi.voting.util.exception.NotFoundException;
import com.restapi.voting.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.restapi.voting.RestaurantTestData.*;
import static com.restapi.voting.TestUtil.readFromJson;
import static com.restapi.voting.TestUtil.userHttpBasic;
import static com.restapi.voting.UserTestData.ADMIN_USER;


class AdminRestaurantControllerTest extends AbstractTestController {
    private static final String REST_URL = AdminRestaurantController.REST_URL + "/";

    @Autowired
    private AdminRestaurantController controller;

    @Test
    @Transactional
    void create() throws Exception {
        Restaurant newRestaurant = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
                .with(userHttpBasic(ADMIN_USER)));
        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(newRestaurant, created);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdated();
        int id = RESTAURANT_1.getId();
        perform(MockMvcRequestBuilders.put(REST_URL + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN_USER)))
                .andExpect(status().isNoContent());
        RESTAURANT_MATCHER.assertMatch(controller.get(id), updated);
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1.getId())
                .with(userHttpBasic(ADMIN_USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND)
                .with(userHttpBasic(ADMIN_USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_2.getId())
                .with(userHttpBasic(ADMIN_USER)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT_2.getId()));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND)
                .with(userHttpBasic(ADMIN_USER)))
                .andExpect(status().isUnprocessableEntity());
    }

}