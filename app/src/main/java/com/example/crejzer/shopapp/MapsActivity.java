package com.example.crejzer.shopapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
//import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import static android.content.Context.LOCATION_SERVICE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String TAG = "MapsActivity";

    public void askApi() {
        Log.d(TAG, "askApi ");
        GsonBuilder builder = new GsonBuilder();
        //tell GSON which object type you want to get
        Type collectionType = new TypeToken<LatLng>() {
        }.getType();
        //tell GSON which deserilizer can transfrom Json into your object
        builder.registerTypeAdapter(collectionType, new GoogleDeserializwer());
        Gson gson = builder.create();
        GsonConverter converter = new GsonConverter(gson);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(API.ENDPOINT)
                .setConverter(converter)
                .build();

        API api = adapter.create(API.class);
        Log.d(TAG, "askApi :");
        api.getListOfPlaces(API.testLocation, 2000, "lewiatan", API.key, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement objj, Response response) {
                Log.d("odpowiedz", "success() called with: " + "latLng = [" + objj + "], response = [" + response + "]");
                JsonObject obj = objj.getAsJsonObject();
                JsonArray arr = obj.get("results").getAsJsonArray();
                JsonObject location = arr.get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
                Double lat = location.get("lat").getAsDouble();
                Double lng = location.get("lng").getAsDouble();
                Log.d(TAG, "success : lat"+String.valueOf(lat));
                Log.d(TAG, "success : lat"+String.valueOf(lng));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "failure : url = " + error.getUrl());
                Log.e(TAG, "cause" + error.getCause());
                Log.e(TAG, "message " + error.getMessage());
                error.printStackTrace();
            }


        });

    }

    private GoogleMap mMap;
    float newLocationAccuracy = 0;
    ArrayList<String> towary = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = new Intent(this, Menu.class);
        mapFragment.startActivityForResult(intent, 222);
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        askApi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("tagg", "onActivityResult() called with " + "requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data.getStringArrayListExtra("towary") + "]");
        switch (resultCode) {
            default:
                Log.d("error", String.valueOf(resultCode));
                break;
            case 222:
                towary = data.getStringArrayListExtra("towary");
//                for(int i = 0; i < towary.size(); i++) {
//                    Log.d("towaryApp", towary.get(i));
//                }
//                String[] skladniki = null;
//                for(int i=0;i<towary.size();i++)
//                {
//                   skladniki[i] =towary.get(i).toString();
//                }
//                Log.d("skladniki",skladniki[0]);

//
//                SklepController sc = new SklepController(this, skladniki);  //inicjalizacja
//                SklepSumy[] ss = sc.getSklepySumy();                        //pobieranie sklepów z najniższymi ofertami
                break;
            case 333:

                break;


        }

//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=lewiatan");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);


        super.onActivityResult(requestCode, resultCode, data);
    }

    //    public static void execute() {
//        Map<String, String> comment = new HashMap<String, String>();
//        comment.put("subject", "Using the GSON library");
//        comment.put("message", "Using libraries is convenient.");
//        String json = new GsonBuilder().create().toJson(comment, Map.class);
//        makeRequest("http://192.168.0.1:3000/post/77/comments", json);
//    }
//
//    public static HttpResponse makeRequest(String uri, String json) {
//        try {
//            HttpPost httpPost = new HttpPost(uri);
//            httpPost.setEntity(new StringEntity(json));
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//            return new DefaultHttpClient().execute(httpPost);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    URL url = new URL("http://coolsite.com/coolstuff.js");
//    InputStream in = url.openStream();
//    InputStreamReader reader = new InputStreamReader(in);
    // read the JSON data
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        Log.d("test", "test");


        String s = "{\n" +
                "  \"html_attributions\": [],\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"geometry\": {\n" +
                "        \"location\": {\n" +
                "          \"lat\": 50.053407,\n" +
                "          \"lng\": 19.939675\n" +
                "        }\n" +
                "      },\n" +
                "      \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n" +
                "      \"id\": \"8742f4520e510e2608463dacd9028aaa95bb7a35\",\n" +
                "      \"name\": \"Lewiatan\",\n" +
                "      \"place_id\": \"ChIJs0EwgWxbFkcRxDKrcwOaNr4\",\n" +
                "      \"reference\": \"CmRcAAAAFjjk6ipyc-oV9zKVE64m9NwsAfS1Phh5yr60h8X7Z2iBofA3x58V7HytDp8gTI84cjjzlq0NohNtXcltImUN4vsEMF7u7YVEaPbiZg1sHMQIilANQvW_WGMA9AUTFrGeEhAmQcJI1p5VLJBtGxcIM3fHGhQ8xyUtx0kpNI1LBmgn7s3CZJ6jog\",\n" +
                "      \"scope\": \"GOOGLE\",\n" +
                "      \"types\": [\n" +
                "        \"establishment\"\n" +
                "      ],\n" +
                "      \"vicinity\": \"Stradomska 9, Kraków\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"OK\"\n" +
                "}";
        String fraza = "lat";
        int gdzie = s.indexOf(fraza);
        Log.d("gdzie", String.valueOf(gdzie));
        String result = s.substring(gdzie + 6, gdzie + 15);
        Log.d("result", result);

        String fraza1 = "lng";
        int gdzie1 = s.indexOf(fraza1);
        Log.d("gdzie", String.valueOf(gdzie1));
        String result1 = s.substring(gdzie1 + 6, gdzie1 + 15);
        Log.d("result", result1);


    }


    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
            // mMap.addMarker(new MarkerOptions().position(sydney).title(String.valueOf(location.getLatitude())+ String.valueOf(location.getLongitude())));
            if (newLocationAccuracy != 0) {
                Log.i("lokacja", String.valueOf(location.getAccuracy()));
                if (newLocationAccuracy <= location.getAccuracy()) {
                    newLocationAccuracy = location.getAccuracy();
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
