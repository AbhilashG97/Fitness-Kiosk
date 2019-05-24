package com.example.fitnesskioskclient.recyclerviewadapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitnesskioskclient.R;
import com.example.fitnesskioskclient.pojo.Health;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutRecyclerViewAdapter.WorkoutViewHolder> {

    private ArrayList<Health> healthArrayList;
    private Context context;

    public WorkoutRecyclerViewAdapter(ArrayList<Health> healthArrayList, Context context) {
        this.healthArrayList = healthArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WorkoutViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.workout_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder workoutViewHolder, int position) {
        String workoutName = healthArrayList.get(position).getExName();
        workoutViewHolder.workout.setText(workoutName);
    }

    @Override
    public int getItemCount() {
        return healthArrayList.size();
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_workout_name)
        TextView workout;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
