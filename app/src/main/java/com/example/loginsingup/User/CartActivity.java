package com.example.loginsingup.User;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsingup.model;
import com.example.loginsingup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rv_cart;
    private RVCartAdapter modelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rv_cart =(RecyclerView) findViewById(R.id.rv_cart);
        modelAdapter = new RVCartAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv_cart.setLayoutManager(linearLayoutManager);

        List<model> listMod = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("dataCart");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ss : snapshot.getChildren()) {
                    model m = ss.getValue(model.class);
                    listMod.add(new model(
                            m.getMasp(),
                            m.getTensp(),
                            m.getGia(),
                            m.getSurl()
                    ));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        modelAdapter.setData(listMod);
        rv_cart.setAdapter(modelAdapter);
    }


}