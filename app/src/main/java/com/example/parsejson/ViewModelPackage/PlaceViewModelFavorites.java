package com.example.parsejson.ViewModelPackage;

import android.app.Application;

import java.util.ArrayList;

import androidx.lifecycle.AndroidViewModel;

import com.example.parsejson.DataPackage.MapDBHelperFavorites;
import com.example.parsejson.ModelsPackage.Result;

public class PlaceViewModelFavorites extends AndroidViewModel {

    private MapDBHelperFavorites mapDBHelperFavorites;  // The SQLiteHelper of the app

    public PlaceViewModelFavorites(Application application) {
        super(application);

        mapDBHelperFavorites = new MapDBHelperFavorites(application);
    }

    public ArrayList<Result> getAllPlaces() {
        return mapDBHelperFavorites.getAllMaps();
    }

    public void insertPlace(String name, String address, Double lat, Double lng, String photo) {
        mapDBHelperFavorites.addMap(name, address, lat, lng, photo);
    }

    public void deleteAll() {
        mapDBHelperFavorites.deleteData();
    }

    public void deletePlace(Result result) {
        mapDBHelperFavorites.deleteMap(result);
    }

    public void updatePlace(String name, String address, Double lat, Double lng, String photo, String id) {
        mapDBHelperFavorites.updateMap(name, address, lat, lng, photo, id);
    }

}
