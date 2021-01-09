# REST API using Hibernate/Spring-Boot **without frontend**.
A voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

### USER action list:
* watch all restaurants
* watch menu for each restaurant
* vote on which restaurant they want to have lunch at (one vote per USER)
* change vote the same day if it is before 11:00
* watch all its votes (votes history)

### ADMIN action list:
* save/delete restaurant
* watch all restaurants
* save/delete dish
* watch menu for the restaurant

### To run
`cd dir_to_project`

`mvn spring-boot:run`

### USER actions:
##### get all restaurants
curl -s http://localhost:8080/restaurants --user user1@gmail.com:password
##### get a restaurant menu by today
curl -s http://localhost:8080/profile/votes/restaurants/100005/dishes --user user1@gmail.com:password
##### create a vote for restaurant
curl -s -X POST -d '{"restaurantId":100003}' http://localhost:8080/profile/votes --user user1@gmail.com:password -H "Content-Type: application/json"
##### update a vote for restaurant
curl -s -X PUT -d '{"restaurantId":100002}' http://localhost:8080/profile/votes/100016 --user user1@gmail.com:password -H "Content-Type: application/json"
##### get all its votes (history of user's votes)
curl -s http://localhost:8080/profile/votes --user user1@gmail.com:password

### ADMIN actions:
##### get all Restaurants
curl -s http://localhost:8080/restaurants --user admin@gmail.com:password
##### get a restaurant
curl -s http://localhost:8080/admin/restaurants/100003 --user admin@gmail.com:password
##### create a restaurant
curl -s -X POST -d '{"name":"Created Restaurant"}' http://localhost:8080/admin/restaurants -H "Content-Type: application/json" --user admin@gmail.com:password
##### update a restaurant
curl -s -X PUT -d '{"id":100016,"name":"Updated Name Restaurant"}' http://localhost:8080/admin/restaurants/100016 -H "Content-Type: application/json" --user admin@gmail.com:password
##### delete a restaurant
curl -s -X DELETE http://localhost:8080/admin/restaurants/100003 --user admin@gmail.com:password
##### get a dish
curl -s http://localhost:8080/admin/dishes/100010 --user admin@gmail.com:password
##### create a dish
curl -s -X POST -d '{"restaurantId":100004, "name":"Created Dish", "price":199.00}' http://localhost:8080/admin/dishes -H "Content-Type: application/json" --user admin@gmail.com:password
##### update a dish
curl -s -X PUT -d '{"id":100016,"name":"Updated Dish","restaurantId":100004,"price":299.00,"date":"2020-09-19"}' http://localhost:8080/admin/dishes/100016 -H "Content-Type: application/json" --user admin@gmail.com:password
##### delete a dish
curl -s -X DELETE http://localhost:8080/admin/dishes/100010 --user admin@gmail.com:password
##### get all Dishes for Restaurant
curl -s http://localhost:8080/admin/dishes?id=100004 --user admin@gmail.com:password

