package com.example.fitnesskioskclient.fragments;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitnesskioskclient.R;
import com.example.fitnesskioskclient.utilities.Constants;
import com.ubidots.ApiClient;
import com.ubidots.Value;
import com.ubidots.Variable;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {


    @BindView(R.id.tv_height)
    TextView mTextHeight;

    @BindView(R.id.tv_bmi)
    TextView mTextBmi;

    @BindView(R.id.tv_weight)
    TextView mTextWeight;

    @BindView(R.id.tv_heart_beat)
    TextView mTextHeartBeat;

    private double height, weight, bmi, heartBeat;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        new ApiUbidots().execute();

        return view;
    }


    public class ApiUbidots extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            ApiClient apiClient = new ApiClient(Constants.API_KEY);
            Variable heightVariable = apiClient.getVariable(Constants.HEIGHT_ID);
            Variable weightVariable = apiClient.getVariable(Constants.WEIGHT_ID);
            Variable heartBeatVariable = apiClient.getVariable(Constants.HEART_BEAT_ID);
            Variable BMIVariable = apiClient.getVariable(Constants.BMI_ID);

            Log.v("HEIGHT", getAverageValue(heightVariable.getValues())+"");
            height = getAverageValue(heightVariable.getValues());
            Log.v("WEIGHT", getAverageValue(weightVariable.getValues())+"");
            weight = getAverageValue(weightVariable.getValues());
            Log.v("HEART BEAT", getAverageValue(heartBeatVariable.getValues())+"");
            heartBeat = getAverageValue(heartBeatVariable.getValues());
            Log.v("BMI", getAverageValue(BMIVariable.getValues())+"");
            bmi = getAverageValue(BMIVariable.getValues());
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mTextWeight.setText(String.format("%s", weight));
            mTextHeight.setText(String.format("%s", height));
            mTextHeartBeat.setText(String.format("%s", heartBeat));
            mTextBmi.setText(String.format("%s", bmi));

            try {
                SharedPreferences preferences = getActivity().getApplicationContext()
                        .getSharedPreferences("bmi_values", 0);
                SharedPreferences.Editor editor = preferences.edit();
                Log.v("BMI VALUE STORED", bmi+"");
                editor.putFloat("bmi",  (float) bmi);
                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private double getAverageValue(Value[] values) {

        double result = 0d;

        for(int i=0; i<10; i++) {
            result += values[i].getValue();
        }
        result = result/10;
        return result;
    }

}
