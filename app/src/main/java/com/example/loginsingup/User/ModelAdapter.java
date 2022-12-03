package com.example.loginsingup.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsingup.R;
import com.example.loginsingup.model;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {
    private List<model> mModels;
    private Context context;

    public void setData (Context context,List<model> list) {
        this.context= context;
        this.mModels = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subj,parent,false);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        model mod = mModels.get(position);
        if(mod == null) {
            return;
        }

        Glide.with(holder.imgModel.getContext())
                .load(mod.getSurl())
                .placeholder(R.drawable.html_5)
                .error(R.drawable.java_jsp)
                .into(holder.imgModel);
        holder.tvTitle.setText(mod.getTensp());

        holder.modelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandleClickItem(mod);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModels != null ?  mModels.size() :  0;
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgModel;
        private TextView tvTitle;
        private CardView modelItem;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);

            imgModel = itemView.findViewById(R.id.img_model);
            tvTitle = itemView.findViewById(R.id.tv_model);
            modelItem = itemView.findViewById(R.id.modelItem);
        }
    }

    private void HandleClickItem(model model) {
        Intent intent = new Intent(context,DetailsModel.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataModel",model);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
