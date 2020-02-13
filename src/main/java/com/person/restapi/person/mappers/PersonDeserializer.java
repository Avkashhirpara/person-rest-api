package com.person.restapi.person.mappers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.person.restapi.person.Person;

import java.io.IOException;

public class PersonDeserializer  extends StdDeserializer<Person> {

    public PersonDeserializer() {
        this(null);
    }

    protected PersonDeserializer(Class<Person> vc) {
        super(vc);
    }

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Person person = new Person();
        int id = node.get("id").asInt();
        String first_Name = node.get("first_name").asText(null);
        String last_Name = node.get("last_name").asText(null);
        String favourite_colour = node.get("favourite_colour").asText(null);
        int age = node.get("age").asInt(0);
        if (id != 0) {
            person.setId(id);
        }
        person.setFirst_name(first_Name);
        person.setLast_name(last_Name);
        person.setFavourite_colour(favourite_colour);
        person.setAge(age);
        return person;
    }


}

