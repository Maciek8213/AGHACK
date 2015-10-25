package com.example.crejzer.shopapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by lokalne on 2015-10-25.
 */
public interface API {
    String ENDPOINT = "https://maps.googleapis.com/maps/api/place/nearbysearch";
    //https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&types=museum&key=AIzaSyB-Zmj7hZYu9UQCP4-6SLLIj6FvwQnHLEY
    String testLocation="50.050072,19.940326";
    String key="AIzaSyB-Zmj7hZYu9UQCP4-6SLLIj6FvwQnHLEY";


    @GET("/json")
    void getListOfPlaces(@Query("location") String location, @Query("radius") int radius,
                         @Query("name") String name, @Query("key") String apiKey, Callback<JsonElement> call);
}
