package com.example.parsejson.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.parsejson.CustomAdaptersPackage.CustomAdapterLocation;
import com.example.parsejson.ModelsPackage.LocationModel;
import com.example.parsejson.ModelsPackage.Result;
import com.example.parsejson.R;
import com.example.parsejson.RetrofitPackage.GetDataService;
import com.example.parsejson.RetrofitPackage.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Result> locationList;
    private CustomAdapterLocation customAdapterLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        getMyData();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getMyData() {
        GetDataService apiService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Observable<LocationModel> observable = apiService.getAll("/maps/api/place/nearbysearch/json?location=31.7428444,34.9847567&radius=50000&key=" +
                getString(R.string.api_key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<LocationModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("check", Objects.requireNonNull(e.getMessage()));
            }

            @Override
            public void onNext(LocationModel locationModel) {
                generateDataList(locationModel.getResults());
            }
        });
    }

    private void generateDataList(List<Result> resultList) {
        locationList = new ArrayList<Result>(resultList);
        customAdapterLocation = new CustomAdapterLocation(this, locationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapterLocation);
    }

}
