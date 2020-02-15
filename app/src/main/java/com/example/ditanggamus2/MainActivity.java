package com.example.ditanggamus2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ditanggamus2.Listener.IfirebaseDone;
import com.example.ditanggamus2.Transform.DepthPageTransformer;
import com.example.ditanggamus2.adapter.Adapter_Rekomendasi;
import com.example.ditanggamus2.adapter.adapterSearch;
import com.example.ditanggamus2.model.modelSearch;
import com.example.ditanggamus2.model.model_ListWisata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IfirebaseDone {

    //Start ViewPager
    ViewPager viewPager;
    Adapter_Rekomendasi adapter_rekomendasi;
    IfirebaseDone ifirebaseDone;
    //end Viewpager
    DatabaseReference rekomendasiREF;
    //recycleView
    //DatabaseReference searchREF;
    //adapterSearch adapterUntukSearch;
    //RecyclerView recyclerView;
    //SearchView searchView;
    //ArrayList<modelSearch> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init firebase
        rekomendasiREF = FirebaseDatabase.getInstance().getReference("rekomendasi");
        //recyclerview
        //searchREF= FirebaseDatabase.getInstance().getReference("search");
        //recyclerView = findViewById(R.id.recycleviewSearch);
        //searchView = (SearchView) findViewById(R.id.searchView);
        //init Event
        ifirebaseDone = this;
        loadRekomendasi();
        viewPager = (ViewPager)findViewById(R.id.viewPagerRekomendasi);
        viewPager.setPadding(130,0,130,0);
        //viewPager.setPageTransformer(true,new DepthPageTransformer());

        //btn
        Button btn = (Button)findViewById(R.id.btnJelajahi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent i = new Intent(MainActivity.this,ListWisataTanggamus.class);
                startActivity(i);
            }
        });
    }

    private void loadRekomendasi() {
        rekomendasiREF.addListenerForSingleValueEvent(new ValueEventListener() {
            List<model_ListWisata> rekomendasiList =  new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot RekoomendasiSnapshot : dataSnapshot.getChildren()){
                    rekomendasiList.add(RekoomendasiSnapshot.getValue(model_ListWisata.class));
                }
                ifirebaseDone.onFirebaseLoadSuccess(rekomendasiList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ifirebaseDone.onFirebaseLoadFailed(databaseError.getMessage());

            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<model_ListWisata> rekomendasiList) {
        adapter_rekomendasi = new Adapter_Rekomendasi(this,rekomendasiList);
        viewPager.setAdapter(adapter_rekomendasi);
    }

    @Override
    public void onFirebaseLoadFailed(String m) {
        Toast.makeText(this,"erorr pal = "+m,Toast.LENGTH_SHORT ).show();
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
                //Toast.makeText(this,"ini search",Toast.LENGTH_SHORT ).show();
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
