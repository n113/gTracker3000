package com.example.user.gtracker3000;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    //Координаты для поиска подарков в пределах минска
    double x1 = 53.911112;
    double y1 = 27.409714;
    double r1 = 0;

    double x2 = 53.940784;
    double y2 = 27.673080;
    double r2 = 0;

    double x3 = 53.835465;
    double y3 = 27.533305;
    double r3 = 0;

// А эти координаты для поиска подарков в пределах целой страны
//    double x1 = 56.170736; //56.170736, 28.151242
//    double y1 = 28.151242;
//    double r1 = 0;
//
//    double x2 = 51.262013;//51.262013, 30.539409
//    double y2 = 30.539409;
//    double r2 = 0;
//
//    double x3 = 51.621310;//51.621310, 23.604127
//    double y3 = 23.604127;
//    double r3 = 0;

    EditText rad1;
    EditText rad2;
    EditText rad3;

    Button bStart;
    Button bReset;

    Circle circle1;
    Circle circle2;
    Circle circle3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rad1 = findViewById(R.id.rad1ID);
        rad2 = findViewById(R.id.rad2ID);
        rad3 = findViewById(R.id.rad3ID);

        bStart = findViewById(R.id.startID);
        bReset = findViewById(R.id.resetID);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCircles();
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
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
        //mMap.addMarker(new MarkerOptions().position(minsk).title("Marker in Minsk"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(minsk));
    }

    public void drawCircles() {
        LatLng minsk = new LatLng(53.902121, 27.557031);
        float zoom = 10;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minsk, zoom));

        r1 = stringToDouble(rad1.getText().toString());
        r2 = stringToDouble(rad2.getText().toString());
        r3 = stringToDouble(rad3.getText().toString());


///*Проверка на заполненность полей с радиусами.
//Проверка не рисует круги, пока все три поля не заполнены.
//Решил отказаться от этой затеи, чтоб можно было рисовать по одному кругу,
//не только три сразу*/
// if (r1!=0&r2!=0&r3!=0){
//            circle1 = mMap.addCircle(new CircleOptions()
//                    .center(new LatLng(x1, y1))
//                    .radius(r1)
//                    .strokeColor(0x220000FF)
//                    .fillColor(0x220000FF));
//            circle2 = mMap.addCircle(new CircleOptions()
//                    .center(new LatLng(x2, y2))
//                    .radius(r2)
//                    .strokeColor(0x220000FF)
//                    .fillColor(0x220000FF));
//            circle3 = mMap.addCircle(new CircleOptions()
//                    .center(new LatLng(x3, y3))
//                    .radius(r3)
//                    .strokeColor(0x220000FF)//Color.RED
//                    .fillColor(0x220000FF));
//        }
//        else{
//            Toast.makeText(bStart.getContext(),"Fill all the fields!",Toast.LENGTH_SHORT).show();
//
//        }

        circle1 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(x1, y1))
                .radius(r1)
                .strokeColor(0x220000FF)
                .fillColor(0x220000FF));
        circle2 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(x2, y2))
                .radius(r2)
                .strokeColor(0x220000FF)
                .fillColor(0x220000FF));
        circle3 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(x3, y3))
                .radius(r3)
                .strokeColor(0x220000FF)//Color.RED
                .fillColor(0x220000FF));

    }

    public void resetAll() {
        circle1.remove();
        circle2.remove();
        circle3.remove();
        rad1.setText(null);
        rad2.setText(null);
        rad3.setText(null);
    }

    public double stringToDouble(String string) { //Конвертирует стринг в дабл. Возвращает ноль, если стринг нулл
        Scanner scanner = new Scanner(string);
        double x = 0;
        try {
            x = scanner.nextDouble();
        } catch (Exception e) {
            x = 0;
        }
        return x;
    }

    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
