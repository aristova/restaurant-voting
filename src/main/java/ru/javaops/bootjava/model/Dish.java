package ru.javaops.bootjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

// TODO Add the field date
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    Date date;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "int default 100")
    @Range(min = 0, max = 100000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;


    public Dish(Integer id, String name, int price, Date date) {
        this(name, price, date);
        this.id = id;
    }

    public Dish() {
    }

    public <E extends Enum<E>> Dish(String name, int price, Date date) {
        super();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
