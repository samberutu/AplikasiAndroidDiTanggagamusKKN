package com.example.ditanggamus2;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ditanggamus2.adapter.adapterListWisata;
import com.example.ditanggamus2.model.model_ListWisata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListWisataTanggamus extends AppCompatActivity {


    DatabaseReference ListWIsataREF;
    adapterListWisata adapterUntukListWisata;
    RecyclerView recyclerView;
    //SearchView searchView;
    ArrayList<model_ListWisata> list;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata_tanggamus);

        //back actionbar
        getSupportActionBar().setTitle("Wisata Di Tanggamus" );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListWIsataREF = FirebaseDatabase.getInstance().getReference("ListWisata");
        recyclerView = findViewById(R.id.ViewPagerListWisata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //searchView = (SearchView) findViewById(R.id.searchView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ListWIsataREF.addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList<model_ListWisata> listBos= new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    listBos.add(dataSnapshot1.getValue(model_ListWisata.class));
                }
                adapterUntukListWisata= new adapterListWisata(listBos);
                recyclerView.setAdapter(adapterUntukListWisata);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("salahnya disini broo ListWisataTanggamus");
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_di_tanggamus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.icon_search :
                startActivity(new Intent(this,search.class));
                return true;
            case R.id.about :
                startActivity(new Intent(this,tentang.class));
                return true;
            case R.id.telusuri  :
                startActivity(new Intent(this,search.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
