package com.example.user.gtracker3000;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    double x1=53.911112; //53.911112, 27.409714, 1.5*10.55 /40075*360
    double y1=27.409714;
    double r1=10550;

    double x2=53.940784; //53.940784, 27.673080, 1.5*10.36 /40075*360
    double y2=27.673080;
    double r2=10360;

    double x3=53.835465;//53.835465, 27.533305, 1.5*4.78  /40075*360
    double y3=27.533305;
    double r3=4780;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng minsk = new LatLng(53.902121, 27.557031);
        mMap.addMarker(new MarkerOptions().position(minsk).title("Marker in Minsk"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(minsk));

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(x1, y1))
                .radius(r1)
                .strokeColor(Color.RED)
                .fillColor(0x220000FF));
        Circle circle2 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(x2, y2))
                .radius(r2)
                .strokeColor(Color.RED)
                .fillColor(0x220000FF));
        Circle circle3 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(x3, y3))
                .radius(r3)
                .strokeColor(Color.RED)
                .fillColor(0x220000FF));
    }
}
