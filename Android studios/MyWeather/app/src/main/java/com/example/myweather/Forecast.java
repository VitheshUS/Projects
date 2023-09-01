package com.example.myweather;

import java.util.ArrayList;

public class Forecast {
    private final ArrayList<forecastday> forecastday;

    public Forecast(ArrayList<com.example.myweather.forecastday> forecastday) {
        this.forecastday = forecastday;
    }

    public ArrayList<com.example.myweather.forecastday> getForecastday() {
        return forecastday;
    }

}
