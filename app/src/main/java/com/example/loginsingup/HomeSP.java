package com.example.loginsingup;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loginsingup.Category.Category;
import com.example.loginsingup.Category.CategoryAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.loginsingup.Category.Category;
import com.example.loginsingup.Category.CategoryAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeSP extends Fragment {
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private SearchView searchViewMainUser;

    List<model> listModel = new ArrayList<>();

    public HomeSP() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_s_p, container, false);
        searchViewMainUser = view.findViewById(R.id.searchViewMainUser);

        searchViewMainUser.clearFocus();

        //recycleview danh sach san pham
        rvCategory= view.findViewById(R.id.rv_category);
        categoryAdapter =new CategoryAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rvCategory.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());

        rvCategory.setAdapter(categoryAdapter);
        return view;


    }

    private List<Category> getListCategory() {
        List<Category> listCategory = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("sanpham");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ss : snapshot.getChildren()) {
                    model m = ss.getValue(model.class);
                    listModel.add(new model(
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




        listCategory.add(new Category("Điện thoại",listModel));
        listCategory.add(new Category("Laptop",listModel));
        listCategory.add(new Category("Đồng hồ",listModel));
        listCategory.add(new Category("Khác",listModel));


        return listCategory;
    }
}