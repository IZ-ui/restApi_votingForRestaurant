package com.restapi.voting;

import com.restapi.voting.model.Role;
import com.restapi.voting.model.User;

import static com.restapi.voting.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;

    public static final User USER_1 = new User(USER_ID, "User1", "user1@gmail.com", "password", Role.USER);
    public static final User USER_2 = new User(USER_ID + 1, "User2", "user2@gmail.com", "password", Role.USER);
    public static final User ADMIN_USER = new User(USER_ID + 2, "Admin", "admin@gmail.com", "password", Role.ADMIN);

    public static User getNew() {
        return new User("New User", "NewUser@gmail.com", "password", Role.USER);
    }
}
