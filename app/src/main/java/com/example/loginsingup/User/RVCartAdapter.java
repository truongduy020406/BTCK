package com.example.loginsingup.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsingup.R;
import com.example.loginsingup.model;

import java.util.List;

public class RVCartAdapter extends RecyclerView.Adapter<RVCartAdapter.ModelViewHolder> {
    private Context context;
    private List<model> mListModel;
    private static int soluong = 1;

    public RVCartAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<model> list) {
        this.mListModel = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new ModelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        model mod = mListModel.get(position);
        if(mod == null){
            return;
        }

        Glide.with(holder.img_cart.getContext())
                .load(mod.getSurl())
                .placeholder(R.drawable.html_5)
                .error(R.drawable.java_jsp)
                .into(holder.img_cart);
        holder.tv_ten_cart.setText(mod.getTensp());
        holder.tv_gia_cart.setText(mod.getGia());
    }

    @Override
    public int getItemCount() {
        return mListModel!=null ? mListModel.size() : 0;
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_cart;
        private TextView tv_ten_cart,tv_gia_cart,tv_soluong_cart;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ten_cart = (TextView) itemView.findViewById(R.id.tv_tensp_cart);
            tv_gia_cart = (TextView) itemView.findViewById(R.id.tv_gia_cart);
            tv_soluong_cart = (TextView) itemView.findViewById(R.id.tv_soluong_cart);
            img_cart = (ImageView) itemView.findViewById(R.id.img_cart);

        }
    }

}
