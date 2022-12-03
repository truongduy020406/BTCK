package com.example.loginsingup.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginsingup.model;
import com.example.loginsingup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsModel extends AppCompatActivity {
    TextView tv_masp_det,tv_tensp_det,tv_gia_det,tv_chiTietSP_det;
    ImageView img_det;
    Button btn_gioHang;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_model);

        tv_masp_det = findViewById(R.id.tv_masp_det);
        tv_tensp_det = findViewById(R.id.tv_tensp_det);
        tv_gia_det = findViewById(R.id.tv_gia_det);
        tv_chiTietSP_det = findViewById(R.id.tv_chiTietSP_det);
        img_det = findViewById(R.id.img_det);
        btn_gioHang = findViewById(R.id.btn_gioHang);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle data  = getIntent().getExtras();
        if(data == null) {
            return;
        }

        model model1 =(model) data.get("dataModel");
        tv_tensp_det.setText(model1.getTensp());
        tv_masp_det.setText(model1.getMasp());
        tv_gia_det.setText(model1.getGia());
        Glide.with(img_det.getContext())
                .load(model1.getSurl())
                .placeholder(R.drawable.html_5)
                .error(R.drawable.java_jsp)
                .into(img_det);


        btn_gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("dataCart").child(model1.getMasp().toString()).setValue(model1);

                Intent intent = new Intent(DetailsModel.this, CartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataCart",model1);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }
}