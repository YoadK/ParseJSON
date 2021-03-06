package com.example.parsejson.PagesPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.parsejson.BroadcastReceiversPackage.BroadcastReceiverBattery;
import com.example.parsejson.CustomAdaptersPackage.CustomAdapterLocation;
import com.example.parsejson.ModelsPackage.LocationModel;
import com.example.parsejson.ModelsPackage.Result;
import com.example.parsejson.R;
import com.example.parsejson.RetrofitPackage.GetDataService;
import com.example.parsejson.RetrofitPackage.RetrofitClientInstance;
import com.example.parsejson.ServicesPackage.MyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Result> locationList;
    private CustomAdapterLocation customAdapterLocation;
    private Button btnStartService, btnStopService, btnMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();// for services
        startBroadcastReceiver();
        getMyData();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        }
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        btnStartService = findViewById(R.id.btnStartService);
        btnStopService = findViewById(R.id.btnStopService);
        btnMove = findViewById(R.id.btnMove);
    }

    private void initListeners() {
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnMove.setOnClickListener(this);
    }

    private void startBroadcastReceiver() {
        final IntentFilter filter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        final BroadcastReceiverBattery receiver = new BroadcastReceiverBattery();
        registerReceiver(receiver, filter);
    }

    private void getMyData() {
        //implementing the "GetDataService" interface
        GetDataService apiService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Observable<LocationModel> observable = apiService.getAll("/maps/api/place/nearbysearch/json?location=31.7428444,34.9847567&radius=50000&key=" +
                getString(R.string.api_key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        // "LocationModel" includes the list of "results" (all location data)
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

    //?
    private void generateDataList(List<Result> resultList) {
        locationList = new ArrayList<Result>(resultList);
        customAdapterLocation = new CustomAdapterLocation(this, locationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapterLocation);
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (shouldProvideRationale) {
            startLocationPermissionRequest();
        } else {
            startLocationPermissionRequest();
        }
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartService:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.btnStopService:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.btnMove:
                startActivity(new Intent(this, FavoritesActivity.class));
        }
    }

}
