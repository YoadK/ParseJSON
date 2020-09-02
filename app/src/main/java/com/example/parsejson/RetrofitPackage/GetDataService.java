package com.example.parsejson.RetrofitPackage;

import com.example.parsejson.ModelsPackage.LocationModel;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface GetDataService {

    @GET()
    Observable<LocationModel> getAllMovies(@Url String url);
}
