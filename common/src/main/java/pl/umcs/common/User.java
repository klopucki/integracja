package pl.umcs.common;

import com.google.common.collect.Lists;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    @Enumerated(STRING)
    private Gender gender;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Address> addresses = Lists.newArrayList();

    public User(Long id, String firstName, String lastName, Integer age, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public User(String firstName, String lastName, Integer age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
