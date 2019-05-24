package com.example.fitnesskioskclient;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.fitnesskioskclient.fragments.DashboardFragment;
import com.example.fitnesskioskclient.fragments.NutritionFragment;
import com.example.fitnesskioskclient.fragments.WorkoutFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private boolean isDashboardFragmentOpen = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if(!isDashboardFragmentOpen) {
                            changeFragment(0);
                        }
                        isDashboardFragmentOpen = true;
                        return true;
                    case R.id.workouts:
                        changeFragment(1);
                        isDashboardFragmentOpen = false;
                        return true;
                    case R.id.nutrition_plans:
                        changeFragment(2);
                        isDashboardFragmentOpen = false;
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getFragmentManager().beginTransaction().replace(
                R.id.fl_fragment_container, new DashboardFragment())
                .commit();
        isDashboardFragmentOpen = true;
    }

    private void changeFragment(int position) {

        Fragment newFragment;

        if (position == 0) {
            newFragment = new DashboardFragment();
        } else if (position % 2 != 0) {
            newFragment = new WorkoutFragment();
        } else {
            newFragment = new NutritionFragment();
        }

        getFragmentManager().beginTransaction().replace(
                R.id.fl_fragment_container, newFragment)
                .commit();
    }
}
