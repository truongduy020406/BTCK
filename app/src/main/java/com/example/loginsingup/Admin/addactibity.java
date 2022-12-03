package com.example.loginsingup.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsingup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addactibity extends AppCompatActivity {
    EditText mSP,tSP,Giaca,urlImages;
    Button btnadd,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactibity);

        mSP = findViewById(R.id.txtmaspham);
        tSP = findViewById(R.id.txttenspham);
        Giaca = findViewById(R.id.txtgiasanpham);
        urlImages = findViewById(R.id.txtlinkimage);
        btnadd = findViewById(R.id.btnAdd);
        btnback = findViewById(R.id.btnBack);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
                clearAll();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void InsertData(){
        Map<String, Object> map = new HashMap<>();
        map.put("masp", mSP.getText().toString());
        map.put("tensp", tSP.getText().toString());
        map.put("gia", Giaca.getText().toString());
        map.put("surl", urlImages.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("sanpham").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addactibity.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addactibity.this, "Error while Insertion.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll(){
        mSP.setText("");
        tSP.setText("");
        Giaca.setText("");
        urlImages.setText("");
    }
}