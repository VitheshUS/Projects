package com.example.ult_news;

import java.util.ArrayList;

public class NewsModel {
    private int totalResults;
    private String status;
    private ArrayList<articles> articles;

    public NewsModel( String status,int totalResults, ArrayList<articles> article) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<articles> getarticles() {
//

        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------"+articles.size());
        System.out.println("--------------------------------------");


        return articles;
    }

    public void setNews_article(ArrayList<articles> articles) {
        this.articles = articles;
    }
}
