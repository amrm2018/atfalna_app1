package com.example.atfalna.atfalna_app1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import maes.tech.intentanim.CustomIntent;

public class Create_P_M_Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String Slat, Slng;

    GloablV gloablV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_p_m_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng egypt = new LatLng(30.048001, 31.231549);
        mMap.addMarker(new MarkerOptions().position(egypt).title("your in egypt")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_marker)) ).showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(egypt, 6));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("my location")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_marker))
                ).showInfoWindow();
                Toast.makeText(getApplicationContext(), latLng.latitude + " , " +latLng.longitude, Toast.LENGTH_SHORT).show();
                Slat = String.valueOf((latLng.latitude));
                Slng = String.valueOf((latLng.longitude));
            }
        });
    }

    public void btn_back_m(View view) {

        gloablV = (GloablV) getApplicationContext();

        gloablV.setLat_m(Slat);
        gloablV.setLng_m(Slng);

        Intent inten = new Intent(getApplicationContext(),Create_P_M.class);
        startActivity(inten);

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_P_M_Map.this);

        builder.setMessage("هل اختارت المكان")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gloablV = (GloablV) getApplicationContext();

                        gloablV.setLat_m(Slat);
                        gloablV.setLng_m(Slng);

                        Intent inten = new Intent(getApplicationContext(),Create_P_M.class);
                        startActivity(inten);
                    }
                }).setNegativeButton(" لا ساختار",null).show();

    }


}
