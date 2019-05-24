package com.example.fitnesskioskclient.utilities;

public class Utility {

    public static String getBMIResult(float bmiValue) {
        if(bmiValue >= 25) {
            return "Overweight";
        } else if (bmiValue <=24.9 && bmiValue >=18.5) {
            return "Normal";
        } else {
            return "Underweight";
        }
    }
}
