package com.example.ditanggamus2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ditanggamus2.R;
import com.example.ditanggamus2.model.model_ListWisata;
import com.example.ditanggamus2.preview;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class Adapter_Rekomendasi extends PagerAdapter {

    Context context;
    List<model_ListWisata> list_rekomendasi;
    LayoutInflater inflater;

    public Adapter_Rekomendasi(Context context, List<model_ListWisata> list_rekomendasi) {
        this.context = context;
        this.list_rekomendasi = list_rekomendasi;
        inflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list_rekomendasi.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object );
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        //inflate view
        final View view = inflater.inflate(R.layout.rekomendasi,container,false);

        //imageview
        ImageView gambar = (ImageView)view.findViewById(R.id.gambarRekomendasi);;
        final TextView nama = (TextView)view.findViewById(R.id.NamaRekomendasi);
        final TextView alamat= (TextView)view.findViewById(R.id.AlamatRekomendasi);
        final TextView desc= (TextView)view.findViewById(R.id.descRekomendasi);
        final TextView cuaca= (TextView)view.findViewById(R.id.namaCuacaRekomendasi);
        final TextView lokasiGeo= (TextView)view.findViewById(R.id.lokasiGeoRekomendasi);

        //sett data
        Picasso.with(context).load(list_rekomendasi.get(position).getGambar()).into(gambar);
        nama.setText(list_rekomendasi.get(position).getNama());
        alamat.setText(list_rekomendasi.get(position).getLokasi());
        desc.setText(list_rekomendasi.get(position).getDesc());
        cuaca.setText(list_rekomendasi.get(position).getCuaca());
        lokasiGeo.setText(list_rekomendasi.get(position).getLokasiGeo());

        //click Listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("namanya " + list_rekomendasi.get(position).getNama());
                Intent i = new Intent(view.getContext(), preview.class);
                i.putExtra("nama",nama.getText().toString());
                i.putExtra("lokasiGeo",lokasiGeo.getText().toString());
                i.putExtra("desc",desc.getText().toString());
                i.putExtra("lokasi",alamat.getText().toString());
                view.getContext().startActivity(i);
            }
        });
        container.addView(view);
        return view;
    }
}
