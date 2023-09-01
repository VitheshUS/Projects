package com.example.myweather;

import java.util.ArrayList;

public class forecastday {

    private final ArrayList<hour>hour;

    public forecastday(ArrayList<hour> hour) {
        this.hour = hour;
    }

    public ArrayList<hour> getHour() {
        return hour;
    }

}
