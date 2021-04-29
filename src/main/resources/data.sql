INSERT INTO USERS (EMAIL, FIRST_NAME, PASSWORD)
VALUES ('user@gmail.com', 'User_First', '{noop}password'),
       ('admin@javaops.ru', 'Admin_First', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANTS (NAME)
VALUES ('RESTAURANT-1'),
       ('RESTAURANT-2');

INSERT INTO DISHES (DATE, NAME, PRICE, RESTAURANT_ID)
VALUES ('1980-07-30', 'Soup', 600, 1),
       ('1980-07-30', 'Salad', 800, 2);

INSERT INTO VOTES (DATE, RESTAURANT_ID, USER_ID)
VALUES ('2020-01-30', 1, 2),
       ('2020-01-30', 2, 1);