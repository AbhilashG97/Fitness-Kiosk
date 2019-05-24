package com.example.fitnesskioskclient.recyclerviewadapters;

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

public class NutritionRecyclerViewAdapter extends RecyclerView.Adapter<NutritionRecyclerViewAdapter.NutritionViewHolder> {

    private ArrayList<Health> healthList;

    public NutritionRecyclerViewAdapter(ArrayList<Health> healthArrayList) {
        healthList = healthArrayList;
    }

    @NonNull
    @Override
    public NutritionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NutritionRecyclerViewAdapter.NutritionViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.nutrition_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionViewHolder nutritionViewHolder, int position) {
        String foodItem = healthList.get(position).getFooditem();
        String additionalInformation = healthList.get(position).getAdditionalinfo();

        nutritionViewHolder.foodItem.setText(foodItem);
        nutritionViewHolder.additionalInformation.setText(additionalInformation);
    }

    @Override
    public int getItemCount() {
        return healthList.size();
    }

    public static class NutritionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_food_item)
        TextView foodItem;

        @BindView(R.id.tv_additional_info)
        TextView additionalInformation;

        public NutritionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
