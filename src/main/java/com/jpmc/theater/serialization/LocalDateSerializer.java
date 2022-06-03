package com.jpmc.theater.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class LocalDateSerializer implements JsonSerializer<LocalDate> {
  @Override
  public JsonElement serialize(
      LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
    return new JsonPrimitive(localDate.toString());
  }
}
