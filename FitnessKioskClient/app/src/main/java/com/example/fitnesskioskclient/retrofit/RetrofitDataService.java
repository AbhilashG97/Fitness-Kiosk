package com.example.fitnesskioskclient.retrofit;

import com.example.fitnesskioskclient.pojo.Health;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitDataService {

    @GET("{type}/bmi")
    Call<ArrayList<Health>> getWorkouts(@Path("type") String path,
                                        @Query("name") String name);

    @GET("{type}/bmi")
    Call<ArrayList<Health>> getNutritionPlans(@Path("type") String path,
                                              @Query("name") String name);
}
