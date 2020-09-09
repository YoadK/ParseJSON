package com.example.parsejson.PagesPackage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parsejson.ModelsPackage.Result;
import com.example.parsejson.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initMap();
    }

    private void initMap() {
        result = (Result) Objects.requireNonNull(getIntent().getExtras()).getSerializable(getString(R.string.map_data));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(result.getGeometry().getLocation().getLat(), result.getGeometry().getLocation().getLng());
        mMap.addMarker(new MarkerOptions().position(latLng).title(result.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

}
