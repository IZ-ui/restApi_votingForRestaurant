package com.restapi.voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "restaurant_id"},
        name = "vote_unique_user_date_idx")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "restaurant_id")
    @NotNull
    private int restaurantId;

    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

    public Vote() {
    }

    public Vote(int restaurantId, LocalDate date) {
        this(null, restaurantId, date);
    }

    public Vote(User user, int restaurantId, LocalDate date) {
        this(null, user, restaurantId, date);
    }

    public Vote(Integer id, User user, int restaurantId, LocalDate date) {
        super(id);
        this.user = user;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }
}
