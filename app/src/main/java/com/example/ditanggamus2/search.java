package com.example.ditanggamus2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ditanggamus2.adapter.adapterSearch;
import com.example.ditanggamus2.model.model_ListWisata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search extends AppCompatActivity {

    //recycleView
    DatabaseReference searchREF;
    adapterSearch adapterUntukSearch;
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<model_ListWisata> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //back actionbar
        getSupportActionBar().setTitle("Cari Destinasi Wisata" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recyclerview
        searchREF= FirebaseDatabase.getInstance().getReference("ListWisata");
        recyclerView = findViewById(R.id.recycleviewSearch);
        searchView = (SearchView) findViewById(R.id.searchView);
    }

    //bagian RecycleVIew
    @Override
    protected void onStart() {
        super.onStart();
        if (searchREF!=null){
            searchREF.addListenerForSingleValueEvent(new ValueEventListener() {
                ArrayList<model_ListWisata> listku= new ArrayList<>();
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        listku.add(dataSnapshot1.getValue(model_ListWisata.class));
                    }
                    adapterUntukSearch = new adapterSearch(listku);
                    recyclerView.setAdapter(adapterUntukSearch);
                    //duplikat list entah kenapa
                    list = listku;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(search.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        cekSearch();
    }

    private void cekSearch() {
        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<model_ListWisata> myList = new ArrayList<>();
        for (model_ListWisata object : list){
            if (object.getNama().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
                adapterSearch adapterSearch = new adapterSearch(myList);
                recyclerView.setAdapter(adapterSearch);
            }
        }
    }

}
