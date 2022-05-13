package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.news.R;

public class DashBoardActivity extends AppCompatActivity {
    CardView sport, business, allNews, health;
    RadioButton arabic, english, french;
    Intent intent;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        sport = findViewById(R.id.sportCV);
        health = findViewById(R.id.healthCV);
        allNews = findViewById(R.id.AllNewsCV);
        business = findViewById(R.id.BusinessCV);
        arabic = findViewById(R.id.arabicBtn);
        french = findViewById(R.id.frenchBtn);
        english = findViewById(R.id.englishBtn);


        //  health.setOnClickListener(this::onClick);
        //  allNews.setOnClickListener(this::onClick);
        //   business.setOnClickListener(this::onClick);
        arabic.setOnClickListener(this::onRadioButtonClicked);
        english.setOnClickListener(this::onRadioButtonClicked);
        french.setOnClickListener(this::onRadioButtonClicked);


        sport.setOnClickListener(view -> {
            if (validate()) {
                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "sport");
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Choose news Language ", Toast.LENGTH_SHORT).show();
            }
        });

        allNews.setOnClickListener(view -> {
            if (validate()) {
                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "All");
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Choose news Language ", Toast.LENGTH_SHORT).show();
            }
        });

        health.setOnClickListener(view -> {
            if (validate()) {
                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "health");
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Choose news Language ", Toast.LENGTH_SHORT).show();
            }
        });

        business.setOnClickListener(view -> {

            if (validate()) {
                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "business");
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Choose news Language ", Toast.LENGTH_SHORT).show();
            }
        });


    }
/*
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        if (validate()) {
            if (view.getId() == R.id.sportCV) {
                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "sport");
                startActivity(intent);
            } else if (view.getId() == R.id.healthCV) {
                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "health");
                startActivity(intent);
            } else if (view.getId() == R.id.AllNewsCV) {


                intent = new Intent(getApplicationContext(), NewsUI.class);
                getLanguage(language);
                intent.putExtra("category", "All");
                startActivity(intent);
            } else if (view.getId() == R.id.BusinessCV)
                intent = new Intent(getApplicationContext(), NewsUI.class);
            getLanguage(language);
            intent.putExtra("category", "business");
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "Choose news Language ", Toast.LENGTH_SHORT).show();
    }

 */

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.arabicBtn:
                if (checked)
                    language = "eg";
                break;

            case R.id.englishBtn:
                if (checked)
                    language = "us";
                break;

            case R.id.frenchBtn:
                if (checked)
                    language = "fr";
                break;
        }
    }


    Boolean validate() {
        if (arabic.isChecked()) {
            return true;
        } else if (english.isChecked()) {
            return true;
        } else if (french.isChecked()) {
            return true;

        } else {
            return false;
        }
    }

    public void getLanguage(String language) {
        if (arabic.isChecked()) {
            intent.putExtra("language", language);
            Log.d("btn", "arabic is clicked  " + intent.getStringExtra("language"));
        } else if (english.isChecked()) {
            intent.putExtra("language", language);
            Log.d("btn", "english is clicked  " + intent.getStringExtra("language"));

        } else if (french.isChecked()) {
            intent.putExtra("language", language);
            Log.d("btn", "french is clicked  " + intent.getStringExtra("language"));
        } else {
            Toast.makeText(this, "please choose language", Toast.LENGTH_SHORT).show();

        }
    }
}