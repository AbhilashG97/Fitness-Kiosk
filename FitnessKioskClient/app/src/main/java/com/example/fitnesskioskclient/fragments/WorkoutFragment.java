package com.example.fitnesskioskclient.fragments;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnesskioskclient.R;
import com.example.fitnesskioskclient.recyclerviewadapters.WorkoutRecyclerViewAdapter;
import com.example.fitnesskioskclient.pojo.Health;
import com.example.fitnesskioskclient.retrofit.RetrofitClientInstance;
import com.example.fitnesskioskclient.retrofit.RetrofitDataService;
import com.example.fitnesskioskclient.utilities.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutFragment extends Fragment {

    @BindView(R.id.rv_workout)
    RecyclerView workouts;

    private float bmiValue;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        ButterKnife.bind(this, view);

        SharedPreferences preferences = getActivity().getApplicationContext()
                .getSharedPreferences("bmi_values", 0);
        bmiValue = preferences.getFloat("bmi", 0);
        Log.v("BMI VALUE READ", bmiValue+"");

        fetchWorkouts();

        return view;
    }

    public void fetchWorkouts() {
        RetrofitDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(RetrofitDataService.class);
        Call<ArrayList<Health>> call = service.getWorkouts("workout",
                Utility.getBMIResult(bmiValue));
        call.enqueue(new Callback<ArrayList<Health>>() {
            @Override
            public void onResponse(Call<ArrayList<Health>> call, Response<ArrayList<Health>> response) {
                initializeRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Health>> call, Throwable t) {
                Toast.makeText(getContext(), "Unable to fetch data :(", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void initializeRecyclerView(ArrayList<Health> healthList) {
        WorkoutRecyclerViewAdapter adapter = new WorkoutRecyclerViewAdapter(healthList, getContext());
        workouts.setAdapter(adapter);
    }

}
