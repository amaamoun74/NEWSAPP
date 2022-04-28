package com.example.news.model;

public class Category {
    private String cat_name;
    private String cat_URL;

    public Category(String cat_name, String cat_URL) {
        this.cat_name = cat_name;
        this.cat_URL = cat_URL;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_URL() {
        return cat_URL;
    }

    public void setCat_URL(String cat_URL) {
        this.cat_URL = cat_URL;
    }
}
