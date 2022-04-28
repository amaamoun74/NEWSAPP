package com.example.news.pojo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.news.R;
import com.example.news.model.Article;
import com.example.news.ui.NewsDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<Article> articleArrayList;
    Context context;

    public NewsAdapter(ArrayList<Article> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articleArrayList.get(position);
        holder.newsHeadLine.setText(article.getTitle());
        holder.newsContent.setText(article.getDescription());
        Picasso.get().load(article.getUrlToImage()).into(holder.newsImage);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, NewsDetails.class);
            intent.putExtra("URL",article.getUrl());
            intent.putExtra("Image",article.getUrlToImage());
            intent.putExtra("HeadLine",article.getTitle());
            intent.putExtra("Description",article.getDescription());
            intent.putExtra("Content",article.getContent());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
/*
    public void setList(ArrayList<Article> ArrayList) {
        this.articleArrayList = ArrayList;
        notifyDataSetChanged();
    }
*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView newsHeadLine, newsContent;
        private ImageView newsImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.news_imageView);
            newsHeadLine = itemView.findViewById(R.id.headLine_textView);
            newsContent = itemView.findViewById(R.id.content_textView);


        }
    }
}
