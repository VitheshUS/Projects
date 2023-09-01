package com.example.myweather;

public class condition {

    private final String text;
    private final String icon;

    public condition(String text, String icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

}
