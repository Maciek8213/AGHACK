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

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.LOCATION_SERVICE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


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
                    Log.d("towaryApp", towary.get(i));
                }

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=lewiatan");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
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

//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=lewiatan");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);

    }


    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            if(newLocationAccuracy != 0) {
                Log.i("lokacja", String.valueOf(location.getAccuracy()));
                if (newLocationAccuracy <= location.getAccuracy()){
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
