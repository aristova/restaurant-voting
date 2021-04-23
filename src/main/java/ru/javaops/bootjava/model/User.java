package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users")

public class User extends BaseEntity  {
    public User(Integer id, String email, String firstName, String lastName, String password, Collection<Role> roles) {
        this(email, firstName, lastName, password, EnumSet.copyOf(roles));
        this.id = id;
    }

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {

    }

    public <E extends Enum<E>> User(String email, String firstName, String lastName, String password, EnumSet<E> copyOf) {
        super();
    }

    public void setEmail(String email) {
        this.email = StringUtils.hasText(email) ? email.toLowerCase() : null;
    }

    @Override
    public Integer getId() {
        return null;
    }
        @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + email +
                ", enabled= }";
    }

}
//    private String email;
//
//    private String password;
//
//    private boolean enabled = true;
//
//    private Date registered = new Date();
//
//    @Column
//    @ElementCollection(targetClass = Role.class)
//    private Set<Role> roles;
//
//
////    @Enumerated(EnumType.STRING)
////    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
////            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
////    @Column(name = "role")
////    @ElementCollection(fetch = FetchType.EAGER)
////    private Set<Role> roles;
//
//
//    protected User() {
//    }
//
////    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
////        this(id, name, email, password, DEFAULT_CALORIES_PER_DAY, true, new Date(), EnumSet.of(role, roles));
////    }
//
//    public User(String name, String email, String password, Date date, Role role, Role... roles) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.registered = new Date();
//        this.roles = EnumSet.of(role, roles);
//
//    }
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Date getRegistered() {
//        return registered;
//    }
//
//    public void setRegistered(Date registered) {
//        this.registered = registered;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//
//
//    public String getPassword() {
//        return password;
//    }
//
////    public void setRoles(Collection<Role> roles) {
////        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
////    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", email=" + email +
//                ", name=" + name +
//                ", enabled=" + enabled +
//                ", }";
//    }
