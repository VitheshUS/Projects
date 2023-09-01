package com.example.myweather;

public class Current {

    private String temp_c;
    private condition condition;
    private String wind_kph;
    private String is_day;

    private String last_updated;

    public Current(String temp_c, com.example.myweather.condition condition, String wind_kph, String is_day, String last_updated) {
        this.temp_c = temp_c;
        this.condition = condition;
        this.wind_kph = wind_kph;
        this.is_day = is_day;
        this.last_updated = last_updated;
    }

    public String getTemp_c() {
        return temp_c;
    }
    public String getLast_updated() {
        return last_updated;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public com.example.myweather.condition getCondition() {
        return condition;
    }

    public void setCondition(com.example.myweather.condition condition) {
        this.condition = condition;
    }

    public String getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(String wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getIs_day() {
        return is_day;
    }

    public void setIs_day(String is_day) {
        this.is_day = is_day;
    }
}
