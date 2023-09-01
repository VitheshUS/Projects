package com.example.myweather;

public class hour {

    private final String time;
    private final String temp_c;
    private final condition condition;
    private final String wind_kph;
    private final String is_day;

    public hour(String time, String temp_c, com.example.myweather.condition condition, String wind_kph, String is_day) {
        this.time = time;
        this.temp_c = temp_c;
        this.condition = condition;
        this.wind_kph = wind_kph;
        this.is_day = is_day;
    }

    public String getTime() {
        return time;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public com.example.myweather.condition getCondition() {
        return condition;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    public String getIs_day() {
        return is_day;
    }

}
