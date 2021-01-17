package com.restapi.voting.web;

import com.sun.istack.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.restapi.voting.model.Dish;
import com.restapi.voting.repository.DishRepository;

import java.net.URI;
import java.util.List;

import static com.restapi.voting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    final static String REST_URL = "/admin/dishes";

    private final DishRepository repository;

    public AdminDishController(DishRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Dish get(@PathVariable int id) {
        log.info("get dish with id {} ", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        log.info("create dish {} for restaurant {}", dish, dish.getRestaurantId());
        Dish created = repository.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Dish update(@RequestBody Dish dish, @PathVariable int id) {
        Assert.notNull(dish, "dish must not be null");
        assureIdConsistent(dish, id);
        log.info("update dish {} ", dish);
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish with id {} ", id);
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @GetMapping
    public List<Dish> getAllByRestaurantId(@RequestParam @Nullable int id) {
        log.info("get all dishes by restaurant id {}", id);
        return repository.findAllByRestaurantId(id);
    }
}
