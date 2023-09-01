package com.example.myweather;

public class WeatherModel {

    private Current current;
    private Forecast forecast;

    public WeatherModel(Current current, Forecast forecast) {
        this.current = current;
        this.forecast = forecast;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
