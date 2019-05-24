package com.example.fitnesskioskclient.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Health {

    @SerializedName("additionalinfo")
    @Expose
    private String additionalinfo;
    @SerializedName("b_id")
    @Expose
    private Integer bId;
    @SerializedName("bmi_id")
    @Expose
    private Integer bmiId;
    @SerializedName("ex_name")
    @Expose
    private String exName;
    @SerializedName("exercise_id")
    @Expose
    private String exerciseId;
    @SerializedName("food_id")
    @Expose
    private String foodId;
    @SerializedName("fooditem")
    @Expose
    private String fooditem;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("limiteduse")
    @Expose
    private Integer limiteduse;
    @SerializedName("lower")
    @Expose
    private Double lower;
    @SerializedName("n_id")
    @Expose
    private String nId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nutri_id")
    @Expose
    private String nutriId;
    @SerializedName("upper")
    @Expose
    private Double upper;

    /**
     * No args constructor for use in serialization
     */
    public Health() {
    }

    /**
     * @param foodId
     * @param bmiId
     * @param lower
     * @param fooditem
     * @param bId
     * @param exName
     * @param id
     * @param upper
     * @param exerciseId
     * @param limiteduse
     * @param name
     * @param nutriId
     * @param additionalinfo
     * @param nId
     */
    public Health(String additionalinfo, Integer bId, Integer bmiId, String exName, String exerciseId, String foodId, String fooditem, Integer id, Integer limiteduse, Double lower, String nId, String name, String nutriId, Double upper) {
        super();
        this.additionalinfo = additionalinfo;
        this.bId = bId;
        this.bmiId = bmiId;
        this.exName = exName;
        this.exerciseId = exerciseId;
        this.foodId = foodId;
        this.fooditem = fooditem;
        this.id = id;
        this.limiteduse = limiteduse;
        this.lower = lower;
        this.nId = nId;
        this.name = name;
        this.nutriId = nutriId;
        this.upper = upper;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public Integer getBId() {
        return bId;
    }

    public void setBId(Integer bId) {
        this.bId = bId;
    }

    public Integer getBmiId() {
        return bmiId;
    }

    public void setBmiId(Integer bmiId) {
        this.bmiId = bmiId;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFooditem() {
        return fooditem;
    }

    public void setFooditem(String fooditem) {
        this.fooditem = fooditem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLimiteduse() {
        return limiteduse;
    }

    public void setLimiteduse(Integer limiteduse) {
        this.limiteduse = limiteduse;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
        this.lower = lower;
    }

    public String getNId() {
        return nId;
    }

    public void setNId(String nId) {
        this.nId = nId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNutriId() {
        return nutriId;
    }

    public void setNutriId(String nutriId) {
        this.nutriId = nutriId;
    }

    public Double getUpper() {
        return upper;
    }

    public void setUpper(Double upper) {
        this.upper = upper;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("additionalinfo", additionalinfo).append("bId", bId).append("bmiId", bmiId).append("exName", exName).append("exerciseId", exerciseId).append("foodId", foodId).append("fooditem", fooditem).append("id", id).append("limiteduse", limiteduse).append("lower", lower).append("nId", nId).append("name", name).append("nutriId", nutriId).append("upper", upper).toString();
    }

}
