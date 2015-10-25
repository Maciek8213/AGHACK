package com.example.crejzer.shopapp;//package com.example.crejzer.shopapp;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lokalne on 2015-10-25.
 */
public class GoogleDeserializwer implements JsonDeserializer<LatLng> {
    @Override
    public LatLng deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("TAG4", json.toString());
        JsonObject obj = json.getAsJsonObject();
        JsonArray array = obj.get("results").getAsJsonArray();

        List<LatLng> list = new LinkedList<>();
        for(int j=0;j< array.size();j++){
            JsonObject geometry = array.get(j).getAsJsonObject();
            JsonObject location = geometry.getAsJsonObject();
            Double lat = location.get("lat").getAsDouble();
            Double lng = location.get("lng").getAsDouble();
            list.add(new LatLng(lat,lng));
        }
        return list.get(0);
    }
}
