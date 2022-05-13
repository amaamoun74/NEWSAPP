package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {

    String uRL, image, title, description, content;
    Button readBtn;
    ImageView newsImage;
    TextView headLine, descriptionTV, contentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        uRL = getIntent().getStringExtra("URL");
        image = getIntent().getStringExtra("Image");
        title = getIntent().getStringExtra("HeadLine");
        description = getIntent().getStringExtra("Description");
        content = getIntent().getStringExtra("Content");

        readBtn = findViewById(R.id.read_btn);
        newsImage = findViewById(R.id.newsImage);
        headLine = findViewById(R.id.headline_TV);
        descriptionTV = findViewById(R.id.description_TV);
        contentTV = findViewById(R.id.content_TV);

        Picasso.get().load(image).into(newsImage);
        headLine.setText(title);
        descriptionTV.setText(description);
        contentTV.setText(content);

        readBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uRL));
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            try {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plan");
                i.putExtra(Intent.EXTRA_SUBJECT, title);
                String body = title + "\n" + uRL + "\n" + "Shared from the News App";
                i.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(i, "Share with :"));

            } catch (Exception e) {
                Toast.makeText(this, "Sorry it Cannot be share", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}