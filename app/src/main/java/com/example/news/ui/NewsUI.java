package com.example.news.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.news.R;
import com.example.news.api.ApiClient;
import com.example.news.api.ApiInterface;
import com.example.news.model.Article;
import com.example.news.model.Category;
import com.example.news.model.News;
import com.example.news.pojo.CategoryAdapter;
import com.example.news.pojo.NewsAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsUI extends AppCompatActivity implements CategoryAdapter.ClickCategoryInterface {
    ProgressBar progressBar;
    RecyclerView newsRecyclerView, categoryRecyclerView;
    private ArrayList<Article> newsArrayList;
    private ArrayList<Category> categoryArrayList;
    CategoryAdapter categoryAdapter;
    NewsAdapter newsAdapter;
    String categoryName, language;
    private static final String api_Key = "791dc72a7bca411b9b212cfcd64e7b4f";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_ui);

        categoryName = getIntent().getStringExtra("category");
        language = getIntent().getStringExtra("language");
       // Log.d("language", language);

        progressBar = findViewById(R.id.progressBar);
        categoryRecyclerView = findViewById(R.id.cat_recyclerview);
        newsRecyclerView = findViewById(R.id.news_recyclerview);
        newsArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryArrayList, this, this);
        newsAdapter = new NewsAdapter(newsArrayList, this);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(newsAdapter);
        categoryRecyclerView.setAdapter(categoryAdapter);
        getCategory();
        getNews(categoryName, language);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_item).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search_item);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search For The Latest News");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0) {
                    newsSearch(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    newsSearch(newText);
                }
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCategory() {
        categoryArrayList.add(new Category("All", "https://images.unsplash.com/photo-1523995462485-3d171b5c8fa9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTl8fG5ld3N8ZW58MHx8MHx8&w=1000&q=80"));
        categoryArrayList.add(new Category("entertainment", "https://images.unsplash.com/photo-1530908295418-a12e326966ba?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjB8fHdlYXRoZXJ8ZW58MHx8MHx8&w=1000&q=80"));
        categoryArrayList.add(new Category("Business", "https://images.unsplash.com/photo-1579532537598-459ecdaf39cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"));
        categoryArrayList.add(new Category("sports", "https://images.unsplash.com/photo-1558365849-6ebd8b0454b2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388&q=80"));
        categoryArrayList.add(new Category("technology", "https://images.unsplash.com/photo-1593349480506-8433634cdcbe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
        categoryArrayList.add(new Category("health", "https://images.unsplash.com/photo-1620648378507-4ad3fac3bb51?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=451&q=80"));
        categoryArrayList.add(new Category("science", "https://images.unsplash.com/photo-1593349480506-8433634cdcbe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"));
      //  categoryArrayList.add(new Category("Alahly SC", "https://i.pinimg.com/736x/fb/f4/7d/fbf47dbe695337e8565db74bb58862ea.jpg"));
        categoryAdapter.notifyDataSetChanged();
    }

    private void getNews(String categoryName, String language) {
        progressBar.setVisibility(View.VISIBLE);
        newsArrayList.clear();
        Call<News> callNews;
        if (categoryName.equals("All")) {
            //  callNews = ApiClient.retrofitInstance().create(ApiInterface.class).getAllNews(new Constants().getCountry(), api_Key);
            callNews = ApiClient.retrofitInstance().create(ApiInterface.class).getAllNews(language, api_Key);
        } else {
            //callNews = ApiClient.retrofitInstance().create(ApiInterface.class).getCategorizedNews(new Constants().getCountry(), categoryName, api_Key);
            callNews = ApiClient.retrofitInstance().create(ApiInterface.class).getCategorizedNews(language, categoryName, api_Key);
        }
        callNews.enqueue(new Callback<News>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                getRsponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Toast.makeText(NewsUI.this, "error, please try agian or check the internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void newsSearch(String keyword) {
        newsArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);
        Call<News> callNews = ApiClient.retrofitInstance()
                .create(ApiInterface.class)
                .getBySearch(keyword
                        , "publishedAt"
                        , api_Key);

        callNews.enqueue(new Callback<News>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                getRsponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Toast.makeText(NewsUI.this, "error, please try agian or check the internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void categoryClicking(int position) {
        String categoryName = categoryArrayList.get(position).getCat_name();
        Log.d("category name", categoryName);
        getNews(categoryName, language);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getRsponse(Response<News> response) {
        Log.d("response", response.toString());
        News news = response.body();
        progressBar.setVisibility(View.GONE);
        ArrayList<Article> article;
        if (news != null) {
            article = news.getArticles();
            for (int i = 0; i < article.size(); i++) {
                newsArrayList.add(new Article(article.get(i).getSource(),
                        article.get(i).getAuthor(),
                        article.get(i).getTitle(),
                        article.get(i).getDescription(),
                        article.get(i).getUrl(),
                        article.get(i).getUrlToImage(),
                        article.get(i).getPublishedAt(),
                        article.get(i).getContent()));
                Log.d("data", article.get(i).getUrl());
            }
            newsAdapter.notifyDataSetChanged();
        }/* else
            Log.d("error", response.body() + "!!!");
        Toast.makeText(NewsUI.this, "no news ...", Toast.LENGTH_SHORT).show();
    }*/
    }
}