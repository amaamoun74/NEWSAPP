package com.example.news.pojo;

import java.util.Locale;

public class Constants {
    public Constants() {
    }

    public static String getCountry() {
        Locale locale = Locale.getDefault();
        return locale.getCountry().toLowerCase();
    }
}
