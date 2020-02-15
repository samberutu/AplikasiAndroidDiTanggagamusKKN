package com.example.ditanggamus2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ditanggamus2.Listener.IfirebaseDonePreview;
import com.example.ditanggamus2.Transform.ZoomOutSlideTransformer;
import com.example.ditanggamus2.adapter.adapterPreview;
import com.example.ditanggamus2.model.modelPreview;
import com.example.ditanggamus2.apiCuaca.cuacaKotaAgung;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//SUHU
import com.androdocs.httprequest.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.AsyncTask;
import android.os.Bundle;

public class preview extends AppCompatActivity implements IfirebaseDonePreview {

    //Start ViewPager
    ViewPager viewPager;
    adapterPreview adapterPreview;
    IfirebaseDonePreview ifirebaseDone;
    DatabaseReference RefPreview;
    Context context;
    //end Viewpager

    //intent
    String namaTitle,lokasiGeo,descEx,lokasiKu;
    //INI SUHU
    TextView suhu,nama,desc,lokasi,cuaca;
    String kota="Kotaagung, ID";
    String API = "d554c7672ac3190dfd96a2211d3935ea";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        //Intent
        Intent intent = getIntent();
        namaTitle = intent.getStringExtra("nama");
        lokasiGeo =intent.getStringExtra("lokasiGeo");
        descEx = intent.getStringExtra("desc") ;
        lokasiKu = intent.getStringExtra("lokasi") ;
        //System.out.println("lokasi" + lokasiGeo);
        getSupportActionBar().setTitle("Preview "+namaTitle );

        //INI UNTUK SUHU
        suhu = (TextView) findViewById(R.id.tvCuaca);
        cuaca = (TextView) findViewById(R.id.inicuaca);
        nama = (TextView) findViewById(R.id.textView2);
        desc = (TextView) findViewById(R.id.descPreview);
        lokasi= (TextView) findViewById(R.id.lokasiPreview);
        nama.setText(namaTitle);
        desc.setText(descEx);
        lokasi.setText(lokasiKu);
        new weatherTask().execute();
        //AKHIR SUHUU

        //RecycleView
        RefPreview = FirebaseDatabase.getInstance().getReference(namaTitle);
        viewPager = findViewById(R.id.ViewPagerPreview);
        ifirebaseDone = this;
        loadPreview();
        viewPager = (ViewPager)findViewById(R.id.ViewPagerPreview);
        //Akhir RecycleVIew

        //btn
        ImageButton btn = (ImageButton)findViewById(R.id.btnLokasi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri ref= Uri.parse(lokasiGeo);
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(ref);
                startActivity(i);
            }
        });

    }

    private void loadPreview() {
        RefPreview.addListenerForSingleValueEvent(new ValueEventListener() {
            List<modelPreview> rekomendasiList =  new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot RekoomendasiSnapshot : dataSnapshot.getChildren()){
                    rekomendasiList.add(RekoomendasiSnapshot.getValue(modelPreview.class));
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
    public void onFirebaseLoadSuccess(List<modelPreview> modelPreviews) {
        adapterPreview = new adapterPreview(this,modelPreviews);
        viewPager.setAdapter(adapterPreview);

    }

    @Override
    public void onFirebaseLoadFailed(String m) {
        Toast.makeText(this,"erorr pal = "+m,Toast.LENGTH_SHORT ).show();
    }


    //bagian cuaca
    class weatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + kota + "&units=metric&appid=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObj = new JSONObject(s);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
                Long updatedAt = jsonObj.getLong("dt");
                String temp = main.getString("temp") + "°C";
                String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
                String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
                String weatherDescription = weather.getString("description");
                /* Populating extracted data into our views */
                suhu.setText(temp);
                cuaca.setText(weatherDescription);

            } catch (JSONException e) {
                Toast.makeText(context,"salah : "+e,Toast.LENGTH_SHORT).show();
            }
        }
    }

}
