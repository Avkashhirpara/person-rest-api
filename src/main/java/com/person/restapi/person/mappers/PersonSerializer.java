package com.person.restapi.person.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.person.restapi.hobby.Hobby;
import com.person.restapi.person.Person;

import java.io.IOException;

/**
 * Deserializer for Person entity. Used by Object Mapper
 * to convert Person entity to its json representation.
 * @author Avkash
 * @version v1
 */
public class PersonSerializer extends StdSerializer<Person> {

    public PersonSerializer() {
        this(null);
    }

    protected PersonSerializer(Class<Person> t) {
        super(t);
    }

    /**
     * Convert Person entity to json representation
     * @param person
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", person.getId());

        jsonGenerator.writeStringField("first_name", person.getFirst_name());
        jsonGenerator.writeStringField("last_name", person.getLast_name());
        jsonGenerator.writeNumberField("age", person.getAge());
        jsonGenerator.writeStringField("favourite_colour", person.getFavourite_colour());

        //write hobby set
        jsonGenerator.writeArrayFieldStart("hobby");

        for (Hobby hobby: person.getHobby()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", hobby.getId());
            jsonGenerator.writeStringField("name", hobby.getName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
