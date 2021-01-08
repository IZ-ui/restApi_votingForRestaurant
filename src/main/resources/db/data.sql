DELETE FROM vote;
DELETE FROM dish;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@gmail.com', '{noop}password'),  --100000
       ('User2', 'user2@gmail.com', '{noop}password'),  --100001
       ('Admin', 'admin@gmail.com', '{noop}password');  --100002

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100002);

INSERT INTO restaurant (name)
VALUES ('KFC'),                                         --100003
       ('McDonalds'),                                   --100004
       ('Burger King');                                 --100005

INSERT INTO dish (RESTAURANT_ID, NAME, PRICE, DATE)
VALUES (100003, 'Longer', 99.00, '2021-01-06'),        --100006
       (100003, 'Nestea', 69.00, '2021-01-06'),        --100007
       (100003, 'Twister', 199.00, '2021-01-06'),      --100008
       (100004, 'Milk Shake', 99.00, CURRENT_DATE),    --100009
       (100004, 'Hamburger', 99.00, CURRENT_DATE),     --100010
       (100004, 'Big Tasty', 149.00, CURRENT_DATE),    --100011
       (100005, 'Whopper', 136.00, CURRENT_DATE),      --100012
       (100005, 'Cezar Roll', 159.00, CURRENT_DATE),   --100013
       (100005, 'Pepsi', 89.00, CURRENT_DATE);         --100014

INSERT INTO vote (USER_ID, RESTAURANT_ID, DATE)
VALUES (100000, 100003, '2021-01-06');                --100015