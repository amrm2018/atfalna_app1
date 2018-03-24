package com.example.atfalna.atfalna_app1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_show_palce_f extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Double lat, lng;
    String slat, slng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_show_palce_f);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        slat = intent.getExtras().getString("key_show_lat_f");
        slng = intent.getExtras().getString("key_show_lng_f");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        lat = Double.valueOf(slat);
        lng = Double.valueOf(slng);

        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title("مكان الحالة"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

        CameraPosition cp =CameraPosition.builder().target(sydney).zoom(11).bearing(90).build();
         mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp),2000 ,null);

         mMap.addCircle(new CircleOptions()
         .center(sydney).radius(1500).strokeColor(R.color.colorRed).fillColor(R.color.colorLightBlue) );


    }
}
