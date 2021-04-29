package ru.javaops.bootjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "unique_date_user_idx")})
public class Vote extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotBlank
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    @NotBlank
    @Temporal(TemporalType.DATE)
    Date date;

    public Vote(Integer id, Restaurant restaurant, User user) {
        this(new Date(), restaurant, user);
        this.id = id;
    }

    public Vote() {
    }

    public <E extends Enum<E>> Vote(Date date, Restaurant restaurant, User user) {
        super();
    }

    @Override
    public Integer getId() {
        return null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
