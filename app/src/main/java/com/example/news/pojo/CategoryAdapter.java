package com.example.news.pojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {
     ArrayList<Category> categoryArrayList ;
     Context context;
     ClickCategoryInterface categoryClick;

    public CategoryAdapter(ArrayList<Category> categoryArrayList, Context context, ClickCategoryInterface categoryClick) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
        this.categoryClick = categoryClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category categoryModel = categoryArrayList.get(position);
        holder.catText.setText(categoryModel.getCat_name());
        Picasso.get().load(categoryModel.getCat_URL()).into(holder.catImage);
        holder.itemView.setOnClickListener(view -> categoryClick.categoryClicking(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    /*
    public void setList(ArrayList<Category> ArrayList) {
        this.categoryArrayList = ArrayList;
        notifyDataSetChanged();
    }
*/

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView catText;
        private ImageView catImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.category_imageView);
            catText = itemView.findViewById(R.id.category_textView);
        }
    }

    public interface ClickCategoryInterface{
        void categoryClicking(int position);

    }
}
