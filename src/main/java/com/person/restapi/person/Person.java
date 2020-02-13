package com.person.restapi.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.person.restapi.hobby.Hobby;
import com.person.restapi.person.mappers.PersonDeserializer;
import com.person.restapi.person.mappers.PersonSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="person")
@JsonSerialize(using = PersonSerializer.class)
@JsonDeserialize(using = PersonDeserializer.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String first_name;
    private  String last_name;
    private  String favourite_colour;
    private  int age;
    @JsonIgnore
    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "person", fetch = FetchType.EAGER)
    private Set<Hobby> hobby = new HashSet<>();

    public Person(){}

    public Person(String first_name, String last_name, String favourite_colour, int age, Set<Hobby> hobby) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.favourite_colour = favourite_colour;
        this.age = age;
        this.hobby = hobby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFavourite_colour() {
        return favourite_colour;
    }

    public void setFavourite_colour(String favourite_colour) {
        this.favourite_colour = favourite_colour;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Hobby> getHobby() {
        return hobby;
    }

    public void setHobby(Set<Hobby> hobby) {
        this.hobby = hobby;
    }

    public void addHobby(Hobby hobby) {
        this.hobby.add(hobby);
        hobby.getPerson().add(this);
    }

    public void removeHobby(Hobby hobby) {
        this.hobby.remove(hobby);
        hobby.getPerson().remove(this);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id:" + id +
                ", first_name=:" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", favourite_colour='" + favourite_colour + '\'' +
                ", age=" + age +
                ", hobby=" + hobby +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != person.getId()) return false;
        if (getAge() != person.getAge()) return false;
        if (getFirst_name() != null ? !getFirst_name().equals(person.getFirst_name()) : person.getFirst_name() != null)
            return false;
        if (getLast_name() != null ? !getLast_name().equals(person.getLast_name()) : person.getLast_name() != null)
            return false;
        if (getFavourite_colour() != null ? !getFavourite_colour().equals(person.getFavourite_colour()) : person.getFavourite_colour() != null)
            return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getFirst_name() != null ? getFirst_name().hashCode() : 0);
        result = 31 * result + (getLast_name() != null ? getLast_name().hashCode() : 0);
        result = 31 * result + (getFavourite_colour() != null ? getFavourite_colour().hashCode() : 0);
        result = 31 * result + getAge();
        return result;
    }
}
