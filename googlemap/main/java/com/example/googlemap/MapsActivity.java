package com.example.googlemap;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private  LatLng shinchon,yu,hd,uri;
    Button  button, button2;
    Polyline polyline1;
    Polygon polygon1;
    boolean clicked, clicked2 =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        button = findViewById(R.id.button);
        //라인 연결해 주는 버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    polygon1.remove();
                }//if
//                    Log.d("버튼 담긴 값",  String.valueOf(clicked));
                    polyline1 = mMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .add(shinchon, yu, hd)
                    );
                mMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
                    @Override
                    public void onPolylineClick(Polyline polyline) {
                        polyline1.setColor(Color.RED);
                    }
                });
                    stylePolyline(polyline1);
                    clicked = true;
            }
        });
        //폴리곤 만드는 버튼
        button2 = findViewById(R.id.polygonButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked2) {
                    polyline1.remove();
                }//if
                    polygon1 = mMap.addPolygon(new PolygonOptions()
                            .clickable(true)
                            .add(shinchon, yu, hd, uri)
                    );
                mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
                    @Override
                    public void onPolygonClick(Polygon polygon) {
                        polygon1.setStrokeColor(Color.RED);
                    }
                });
                    stylePolygon(polygon1);
                    clicked2 = true;
                }
        });
    }//onCreate
    //라인 메서드
    public void stylePolyline(Polyline polyline1){
        polyline1.setWidth(12);
        polyline1.setColor(Color.BLUE);
    }
    //폴리곤 메서드
    public void stylePolygon(Polygon polygon1){
        polygon1.setStrokeWidth(12);
        polygon1.setStrokeColor(Color.BLUE);
        polygon1.setFillColor(Color.GREEN);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //연세대 37.56587752652941, 126.93852908230728
        //현대백화점 37.55616511231137, 126.93564975772355
        //신촌우리은행 37.5551370168183, 126.93752052604965
        // Add a marker in Sydney and move the camera
        shinchon = new LatLng(37.559722, 126.943056);
        yu = new LatLng(37.56587752652941, 126.93852908230728);
        hd = new LatLng(37.55616511231137, 126.93564975772355);
        uri = new LatLng(37.5551370168183, 126.93752052604965);
        mMap.addMarker(new MarkerOptions().position(shinchon).title("신촌역"));
        mMap.addMarker(new MarkerOptions().position(yu).title("연세대학교"));
        mMap.addMarker(new MarkerOptions().position(hd).title("현대백화점"));
        mMap.addMarker(new MarkerOptions().position(uri).title("우리은행"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(shinchon));
    }
}