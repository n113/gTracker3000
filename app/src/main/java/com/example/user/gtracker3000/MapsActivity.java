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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    //Координаты для поиска подарков в пределах минска/ startPoints
    //TODO change name of the x and y to starPointX1
    double defStartPointX1 = 53.911112;
    double defStartPointY1 = 27.409714;
    double r1 = 0;

    double defStartPointX2 = 53.940784;
    double defStartPointY2 = 27.673080;
    double r2 = 0;

    double defStartPointX3 = 53.835465;
    double defStartPointY3 = 27.533305;
    double r3 = 0;

    LatLng minskCityCenter = new LatLng(53.902121, 27.557031);
    LatLng defPoint1 = new LatLng(defStartPointX1, defStartPointY1);
    LatLng defPoint2 = new LatLng(defStartPointX2,defStartPointY2);
    LatLng defPoint3 = new LatLng(defStartPointX3,defStartPointY3);



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

    public Marker marker1;
    public Marker marker2;
    public Marker marker3;


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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        float zoom = 10; //focus on center of the City
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(minskCityCenter, zoom));

        marker1 = mMap.addMarker(new MarkerOptions()
                .position(defPoint1).title("Circle1").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        marker2 = mMap.addMarker(new MarkerOptions()
                .position(defPoint2).title("Circle2").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        marker3 = mMap.addMarker(new MarkerOptions()
                .position(defPoint3).title("Circle3").draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }
        });
    }




    public void drawCircles() {
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
                .center(new LatLng(marker1.getPosition().latitude, marker1.getPosition().longitude))
                .radius(r1)
                .strokeColor(0x22f52ee5)
                .fillColor  (0x22f52ee5));
        circle2 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(marker2.getPosition().latitude, marker2.getPosition().longitude))
                .radius(r2)
                .strokeColor(0x222ef546)
                .fillColor  (0x222ef546));
        circle3 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(marker3.getPosition().latitude, marker3.getPosition().longitude))
                .radius(r3)
                .strokeColor(0x221aced5)//Color.RED
                .fillColor  (0x221aced5));

    }

    public void resetAll() {
        circle1.remove();
        circle2.remove();
        circle3.remove();
        rad1.setText(null);
        rad2.setText(null);
        rad3.setText(null);
       // makeToast(mSydney.getPosition().toString());
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
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}
