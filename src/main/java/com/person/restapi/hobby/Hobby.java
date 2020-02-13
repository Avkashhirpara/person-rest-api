package com.person.restapi.hobby;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.person.restapi.hobby.mappers.HobbyDeserializer;
import com.person.restapi.hobby.mappers.HobbySerializer;
import com.person.restapi.person.Person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="hobby")
@JsonSerialize(using = HobbySerializer.class)
@JsonDeserialize(using = HobbyDeserializer.class)
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String name;

    @JsonIgnore
    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "person_hobby", joinColumns = {@JoinColumn(name = "hobby_id")}
    , inverseJoinColumns = {@JoinColumn(name = "person_id")} )
    private Set<Person> person = new HashSet<>();

    public Hobby(String name) {
        this.name = name;
    }

    public Hobby() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPerson() {
        return person;
    }

    public void setPerson(Set<Person> person) {
        this.person = person;
    }

    public void addPerson(Person person) {
        this.person.add(person);
        person.getHobby().add(this);
    }

    public void removePerson(Person person) {
        this.person.remove(person);
        person.getHobby().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hobby)) return false;
        Hobby hobby = (Hobby) o;
        if (!getName().equals(hobby.getName())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
