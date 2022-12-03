package com.example.loginsingup.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsingup.R;
import com.example.loginsingup.User.ModelAdapter;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private Context mContext;
    private List<Category> mListCategory;

    public CategoryAdapter (Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category> list) {
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if(category == null) {
            return;
        }

        holder.tvNameCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        holder.rvSubject.setLayoutManager(linearLayoutManager);

        ModelAdapter subjectAdapter = new ModelAdapter();
        subjectAdapter.setData(mContext,category.getModels());

        holder.rvSubject.setAdapter(subjectAdapter);


    }

    @Override
    public int getItemCount() {
        return mListCategory != null ? mListCategory.size(): 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameCategory;
        private RecyclerView rvSubject;
        private CardView modelItem;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_category);
            rvSubject = itemView.findViewById(R.id.rv_subject);
            modelItem = itemView.findViewById(R.id.modelItem);
        }
    }


}


