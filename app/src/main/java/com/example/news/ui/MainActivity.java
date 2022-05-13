package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;
import com.example.news.R;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView animationView;
    private static final int splashDuration= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationView = findViewById(R.id.animationView);
        animationView.animate().setStartDelay(500);
        // momkn yb2a f error f l handler bsbb l import
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
            startActivity(intent);
            finish();
        },splashDuration);

    }
}