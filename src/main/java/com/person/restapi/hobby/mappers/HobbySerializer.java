package com.person.restapi.hobby.mappers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.person.restapi.hobby.Hobby;

import java.io.IOException;
/**
 * Deserializer for Hobby entity. Used by Object Mapper
 * to convert Hobby entity to its json represantation
 * @author Avkash
 * @version v1
 */
public class HobbySerializer extends StdSerializer<Hobby> {
    public HobbySerializer() {
        this(null);
    }

    protected HobbySerializer(Class<Hobby> t) {
        super(t);
    }

    /**
     * Convert Hobby entity to json representation
     * @param hobby
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(Hobby hobby, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", hobby.getId());
        jsonGenerator.writeStringField("name", hobby.getName());
        jsonGenerator.writeEndObject();

    }
}
