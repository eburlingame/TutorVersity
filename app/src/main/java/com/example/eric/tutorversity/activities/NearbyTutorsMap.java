package com.example.eric.tutorversity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.eric.tutorversity.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class NearbyTutorsMap extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_tutors_map);

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
        GoogleMap mMap = googleMap;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(NearbyTutorsMap.this, ViewTutor.class);
                startActivity(intent);
            }
        });

        LatLng slo = new LatLng(35.3050, -120.6625);
        LatLng adam = new LatLng(35.2950, -120.6525);
        LatLng julie = new LatLng(35.3150, -120.6725);
        LatLng matt = new LatLng(35.3080, -120.6700);


        mMap.addMarker(new MarkerOptions().position(slo).title("Cal Poly, San Luis Obispo"));
        mMap.addMarker(new MarkerOptions().position(adam).title("Adam"));
        mMap.addMarker(new MarkerOptions().position(julie).title("Julie"));
        mMap.addMarker(new MarkerOptions().position(matt).title("Matt"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(slo, 14));
    }

}
