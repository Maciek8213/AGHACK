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
import android.widget.Toast;

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
import java.util.SortedSet;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

import static android.content.Context.LOCATION_SERVICE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    ArrayList<Double> wspol = new ArrayList<Double>();
    public static final String TAG = "MapsActivity";

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


    }
//    public void inthread(){
//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {

   -     super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("tagg", "onActivityResult() called with " + "requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data.getStringArrayListExtra("towary") + "]");
        switch(resultCode){
                default:
                    Log.d("error",String.valueOf(resultCode));
                    break;
                case 222:
                    towary = data.getStringArrayListExtra("towary");
                    for(int i = 0; i < towary.size(); i++) {
                        Log.d("odleglosc", towary.get(i));
                    }
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
                            Log.d(TAG, "success : lat" + String.valueOf(lat));
                            Log.d(TAG, "success : lat" + String.valueOf(lng));
                            wspol.add(lat);
                            wspol.add(lng);
                            Log.d("test", String.valueOf(wspol.size()));

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, "failure : url = " + error.getUrl());
                            Log.e(TAG, "cause" + error.getCause());
                            Log.e(TAG, "message " + error.getMessage());
                            error.printStackTrace();
                        }

                    });

                    api.getListOfPlaces(API.testLocation, 2000, "biedronka", API.key, new Callback<JsonElement>() {

                        @Override
                        public void success(JsonElement objj, Response response) {
                            Log.d("odpowiedz", "success() called with: " + "latLng = [" + objj + "], response = [" + response + "]");
                            JsonObject obj = objj.getAsJsonObject();
                            JsonArray arr = obj.get("results").getAsJsonArray();
                            JsonObject location = arr.get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
                            Double lat = location.get("lat").getAsDouble();
                            Double lng = location.get("lng").getAsDouble();
                            Log.d(TAG, "success : lat"+String.valueOf(lat));
                            Log.d(TAG, "success : lat" + String.valueOf(lng));
                            wspol.add(lat);
                            wspol.add(lng);
                            Log.d("test", String.valueOf(wspol.size()));

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, "failure : url = " + error.getUrl());
                            Log.e(TAG, "cause" + error.getCause());
                            Log.e(TAG, "message " + error.getMessage());
                            error.printStackTrace();
                        }

                    });

                    api.getListOfPlaces(API.testLocation, 2000, "Carrefour", API.key, new Callback<JsonElement>() {

                        @Override
                        public void success(JsonElement objj, Response response) {
                            Log.d("odpowiedz", "success() called with: " + "latLng = [" + objj + "], response = [" + response + "]");
                            JsonObject obj = objj.getAsJsonObject();
                            JsonArray arr = obj.get("results").getAsJsonArray();
                            JsonObject location = arr.get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
                            Double lat = location.get("lat").getAsDouble();
                            Double lng = location.get("lng").getAsDouble();
                            Log.d(TAG, "success : lat"+String.valueOf(lat));
                            Log.d(TAG, "success : lat" + String.valueOf(lng));
                            wspol.add(lat);
                            wspol.add(lng);
                            Log.d("test", String.valueOf(wspol.size()));
//                            LatLng sydney = new LatLng(lat, lng);
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("TU KURWA MASZ ISC DO CARREFOURA"));
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, "failure : url = " + error.getUrl());
                            Log.e(TAG, "cause" + error.getCause());
                            Log.e(TAG, "message " + error.getMessage());
                            error.printStackTrace();
                        }

                    });

                    api.getListOfPlaces(API.testLocation, 2000, "Zabka", API.key, new Callback<JsonElement>() {

                        @Override
                        public void success(JsonElement objj, Response response) {
                            Log.d("odpowiedz", "success() called with: " + "latLng = [" + objj + "], response = [" + response + "]");
                            JsonObject obj = objj.getAsJsonObject();
                            JsonArray arr = obj.get("results").getAsJsonArray();
                            JsonObject location = arr.get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
                            Double lat = location.get("lat").getAsDouble();
                            Double lng = location.get("lng").getAsDouble();
                            Log.d(TAG, "success : lat"+String.valueOf(lat));
                            Log.d(TAG, "success : lat" + String.valueOf(lng));
                            wspol.add(lat);
                            wspol.add(lng);
                            Log.d("test", String.valueOf(wspol.size()));
//                            LatLng sydney = new LatLng(lat, lng);
//                            mMap.addMarker(new MarkerOptions().position(sydney).title("TU KURWA MASZ ISC"));
                            Log.d("test",String.valueOf(wspol.size()));
                            double x =50.050072; //pobierane
                            double y =19.940326; //pobierane
                            Double[] odleglosc=new Double[100];
                            //ArrayList<Double> list=new ArrayList<Double>();

//                            ArrayList<Place> places = new ArrayList<Place>();
//                            Place place1 = new Place(wspol.get(0);wspol.get(1));
//                            Place place2 = new Place(wspol.get(2);wspol.get(3));
//                            Place place3 = new Place(wspol.get(4);wspol.get(5));
//                            Place place4 = new Place(wspol.get(6);wspol.get(7));

                            int j=0;
                                for(int i= 0; i < wspol.size()-1; i+=2)
                            {
//
                                odleglosc[j]=(x-wspol.get(i))*(x-wspol.get(i))+(y-wspol.get(i+1))*(y-wspol.get(i+1));
                                j++;
                            }
                            double wynik = odleglosc[0];
                            int n=0;
                            for (int i=1; i<wspol.size()/2; i++) {
                                if (wynik > odleglosc[i]) {
                                    wynik = odleglosc[i];
                                    n=i;
                                }
                            }

                                LatLng sydney = new LatLng(wspol.get(2*n), wspol.get(2*n + 1));
                                mMap.addMarker(new MarkerOptions().position(sydney).title("Najbliższy sklep"));


//

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.e(TAG, "failure : url = " + error.getUrl());
                            Log.e(TAG, "cause" + error.getCause());
                            Log.e(TAG, "message " + error.getMessage());
                            error.printStackTrace();
                        }

                    });

//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=zabka");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
                break;
            case 111:
                towary = data.getStringArrayListExtra("towary");
                String[] pro = new String[100];
                int ilosc_prod = 0;
                for(int i = 0; i < towary.size(); i++) {
                    pro[i]=towary.get(i).toString();
                    ilosc_prod++;
                }
                String[] produkty = new String[ilosc_prod];
                for(int i=0; i<ilosc_prod; i++)
                {
                    produkty[i] = pro[i];
                }

                SklepFactory sklep_factory = new SklepFactory(this);
                ProduktFactory pr_fa = new ProduktFactory(this);

                Sklep[] s2 = sklep_factory.getSklepy();
                int ala = s2.length;
                String [] nazwa = {"Chleb","Maslo","Piwo","Wódka","Szynka/100gg","Ser żółty/150","Jajka/10szt","Sok/1l","Cola/2l","Banany/1kg","Jabłka","Ziemniaki/2.5kg","Cebula/1kg","Pasztet","Makaron"};
                double [] cena_BiedroCena ={3,3.70,2.12,20,3.5,2.90,5,2.80,5,2.7,2.10,3,2.20,1.19,1.90};
                double [] cena_KauflandCena ={2.60,4.55,2.45,21,2.5,3.80,4,2.80,5,2.7,2.10,2.20,1.60,1.90,1.90};
                double [] cena_LewiatanCena={2.49,4.20,2.30,22,4.7,3,4.6,3.10,5.1,2.7,2.20,3.2,2.10,1.45,2.10};
                double [] cena_Zabkacena ={2.29,3.50,2.34,23,5.0,3.0,5,3,4.70,2.5,2.25,3,2.10,1.39,1.70};
                for(int i=0; i< ala; i++)
                {
                    sklep_factory.kasujSklepy(s2[i].getId());
                    pr_fa.kasujProdukty(s2[i].getId());
                }
                MapsActivity polozenie = new MapsActivity();
                sklep_factory.dodajSklep("Biedronka", 0, 0);
                sklep_factory.dodajSklep("Kaufland", 0, 0);
                sklep_factory.dodajSklep("Lewiatan", 0, 0);
                sklep_factory.dodajSklep("Zabka", 0, 0);
                Sklep[] sklepy = sklep_factory.getSklepy();

                for(int i =0 ; i < 15; i++)
                {
                    pr_fa.dodajProdukt(sklepy[0].getId(),nazwa[i],cena_BiedroCena[i],10);
                    pr_fa.dodajProdukt(sklepy[1].getId(),nazwa[i],cena_KauflandCena[i],10);
                    pr_fa.dodajProdukt(sklepy[2].getId(),nazwa[i],cena_LewiatanCena[i],10);
                    pr_fa.dodajProdukt(sklepy[3].getId(),nazwa[i],cena_Zabkacena[i],10);



                }
                sklepy[0].setProdukty(pr_fa.getProduktyBySklepId(sklepy[0].getId()));
                sklepy[1].setProdukty(pr_fa.getProduktyBySklepId(sklepy[1].getId()));
                sklepy[2].setProdukty(pr_fa.getProduktyBySklepId(sklepy[2].getId()));
                sklepy[3].setProdukty(pr_fa.getProduktyBySklepId(sklepy[3].getId()));



                SklepController sc = new SklepController(this, produkty);  //inicjalizacja
                SklepSumy[] ss = sc.getSklepySumy();                        //pobieranie sklepów z najniższymi ofertami

                Log.d("alma", ss[0].getSuma()+"");

                String optShop = ss[0].getNazwa();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+optShop);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                Toast.makeText(this, "Twoje zakupy będą kosztować "+ ss[0].getSuma()+" zł", Toast.LENGTH_LONG).show();
                startActivity(mapIntent);
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }



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




    }


    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
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
