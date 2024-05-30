package com.meilleurtaux.test.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.meilleurtaux.test.record.Town;

import java.io.IOException;

public class TownDeserializer extends JsonDeserializer<Town> {
    @Override
    public Town deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String name = node.get("nom").asText();
        int population = node.get("population").asInt();
        return new Town(name, population);
    }
}
