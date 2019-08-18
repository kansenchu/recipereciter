package com.example.recipereciter;

import com.example.recipereciter.business.dao.RecipeDao;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecipeDaoDeserializer extends StdDeserializer<RecipeDao> {

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private RecipeDaoDeserializer(Class<?> vc) {
    super(vc);
  }

  RecipeDaoDeserializer() {
    this(null);
  }

  @Override
  public RecipeDao deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    RecipeDao.RecipeDaoBuilder builder = RecipeDao.builder();

    builder.id((Integer) node.get("id").numberValue())
          .title(node.get("title").asText())
          .makingTime(node.get("making_time").asText())
          .serves(node.get("serves").asText())
          .ingredients(node.get("ingredients").asText())
          .cost(node.get("cost").asText())
          .createdAt(Timestamp.valueOf(LocalDateTime.parse(node.get("created_at").asText(), formatter)))
          .updatedAt(Timestamp.valueOf(LocalDateTime.parse(node.get("updated_at").asText(), formatter)));

    return builder.build();
  }
}
