package com.example.medic.api_and_model;

import java.util.ArrayList;

public class NewsModel {

private String status;
private String totalResults;
ArrayList<articles> articles;

    public NewsModel(String status, String totalResults, ArrayList<com.example.medic.api_and_model.articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<com.example.medic.api_and_model.articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<com.example.medic.api_and_model.articles> articles) {
        this.articles = articles;
    }
}
