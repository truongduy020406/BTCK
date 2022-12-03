package com.example.loginsingup.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsingup.R;
import com.example.loginsingup.model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends FirebaseRecyclerAdapter<model,Adapter.myviewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull model model) {
        holder.masp.setText(model.getMasp());
        holder.tenSP.setText(model.getTensp());
        holder.gia.setText(model.getGia());


        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();
                View view1 =dialogPlus.getHolderView();

                EditText masp =view1.findViewById(R.id.masanpham);
                EditText tensp =view1.findViewById(R.id.tensanpham);
                EditText gia =view1.findViewById(R.id.giasanpham);
                EditText surl =view1.findViewById(R.id.urlsanpham);

                Button buttonUpdate =view1.findViewById(R.id.btnUpdate);
                masp.setText(model.getMasp());
                tensp.setText(model.getTensp());
                gia.setText(model.getGia());
                surl.setText(model.getSurl());
                dialogPlus.show();

                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("masp",masp.getText().toString());
                        map.put("tensp",tensp.getText().toString());
                        map.put("gia",gia.getText().toString());
                        map.put("surl",surl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("sanpham")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.masp.getContext(), "update thanh cong", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.masp.getContext(), " error ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.masp.getContext());
                builder.setTitle("Bạn có muốn xoá?");
                builder.setMessage("xoá không thành công.");

                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("sanpham")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.masp.getContext(),"Canclled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);


        return new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{
        CircleImageView img ;
        TextView masp,tenSP,gia;
        Button btnEdit, btnDelete;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView)itemView.findViewById(R.id.imgl);
            masp =(TextView)itemView.findViewById(R.id.txtmasp);
            tenSP =(TextView)itemView.findViewById(R.id.txtname);
            gia =(TextView)itemView.findViewById(R.id.txtgia);

            btnEdit = (Button) itemView.findViewById(R.id.btnedit);
            btnDelete = (Button) itemView.findViewById(R.id.btnxoa);
        }
    }
}
