package com.restapi.voting.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name", "date"},
        name = "dishes_unique_restaurant_name_datetime_idx")})
public class Dish extends AbstractNamedEntity {

    @Column(name = "restaurant_id")
    @NotNull
    private int restaurantId;

    @Column(name = "price", nullable = false)
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Positive
    private BigDecimal price;

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat
    private LocalDate date = LocalDate.now();

    public Dish() {
    }

    public Dish(Integer id, String name, BigDecimal price, LocalDate dateTime) {
        super(id, name);
        this.price = price;
        this.date = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDateTime() {
        return date;
    }

    public void setDateTime(LocalDate date) {
        this.date = date;
    }
}
