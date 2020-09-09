package com.example.parsejson.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.parsejson.CustomAdaptersPackage.CustomAdapterLocation;
import com.example.parsejson.ModelsPackage.Result;
import com.example.parsejson.OtherPackage.ConApp;
import com.example.parsejson.R;
import com.example.parsejson.ViewModelPackage.PlaceViewModelFavorites;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Result> locationList = new ArrayList<>();
    private CustomAdapterLocation customAdapterLocation;
    private PlaceViewModelFavorites placeViewModelFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        initUI();
        initRecyclerView();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initRecyclerView() {
        placeViewModelFavorites = new PlaceViewModelFavorites(ConApp.getApplication());
        locationList = placeViewModelFavorites.getAllPlaces();
        customAdapterLocation = new CustomAdapterLocation(this, locationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapterLocation);
    }

}
