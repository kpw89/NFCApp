package com.example.kwest.timel;

import com.example.kwest.timel.Model.TimeLog;
import com.example.kwest.timel.Model.TimeLogString;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DeserializeTimeLog implements JsonDeserializer<TimeLogString> {

    @Override
    public TimeLogString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement timeLog = json.getAsJsonObject().get("timeLog");
        return new Gson().fromJson(timeLog,TimeLogString.class );
    }
}
