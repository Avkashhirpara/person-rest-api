package com.person.restapi.hobby.mappers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.person.restapi.hobby.Hobby;

import java.io.IOException;

/**
 * Deserializer for Hobby entity. Used by Object Mapper
 * to convert httpentity<Hobby> to Hobby entity
 * @author Avkash
 * @version v1
 */
public class HobbyDeserializer extends StdDeserializer<Hobby> {
    public HobbyDeserializer() {
        this(null);
    }

    protected HobbyDeserializer(Class<Hobby> vc) {
        super(vc);
    }


    /**
     * Convert json representation to java object of Hobby entity.
     * @param jsonParser
     * @param deserializationContext
     * @return Hobby
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public Hobby deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Hobby hobby = new Hobby();
        int id = 0;
        if(node.has("id")) {
            id = node.get("id").asInt(0);
        }
        String name = node.get("name").asText(null);
        hobby.setName(name);
        hobby.setId(id);
        return hobby;

    }
}
