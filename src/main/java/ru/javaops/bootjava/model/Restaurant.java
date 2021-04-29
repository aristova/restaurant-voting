package ru.javaops.bootjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.EnumSet;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "name_unique_idx")})
public class Restaurant extends BaseEntity {
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    public Restaurant(Integer id, String name) {
        this(name);
        this.id = id;
    }

    public Restaurant() {
    }

    public <E extends Enum<E>> Restaurant(String name) {
        super();
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
}
