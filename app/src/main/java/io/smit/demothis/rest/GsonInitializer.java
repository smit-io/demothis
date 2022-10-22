package io.smit.demothis.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.smit.demothis.rest.gsonserializer.LocalDateDeserializer;
import io.smit.demothis.rest.gsonserializer.LocalDateSerializer;
import io.smit.demothis.rest.gsonserializer.LocalDateTimeDeserializer;
import io.smit.demothis.rest.gsonserializer.LocalDateTimeSerializer;

public final class GsonInitializer
{
    private static GsonInitializer gsonInitializer = null;
    private static Gson gson;
    private static GsonBuilder gsonBuilder;

    private GsonInitializer()
    {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.setLenient();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    private static GsonInitializer getInstance()
    {
        if (gsonInitializer == null)
        {
            gsonInitializer = new GsonInitializer();
        }
        return gsonInitializer;
    }

    public static Gson getGson()
    {
        getInstance();
        return gson;
    }
}
