package com.example.ditanggamus2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ditanggamus2.R;
import com.example.ditanggamus2.model.model_ListWisata;
import com.example.ditanggamus2.preview;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class adapterSearch extends RecyclerView.Adapter<adapterSearch.MyViewHolder> {

    ArrayList<model_ListWisata> listku;
    Context context;
    private ForkJoinPool tts;

    public adapterSearch(ArrayList<model_ListWisata> list) {
        this.listku = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search,parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nama.setText(listku.get(position).getNama());
        holder.desc.setText(listku.get(position).getDesc());
        holder.lokasiGeo.setText(listku.get(position).getLokasiGeo());
        holder.lokasi.setText(listku.get(position).getLokasi());
        holder.cuaca.setText(listku.get(position).getCuaca());
        Picasso.with(context).load(listku.get(position).getGambar()).into(holder.gambar);

    }

    @Override
    public int getItemCount() {
        return listku.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama,desc,lokasiGeo,lokasi,cuaca;
        ImageView gambar;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaSearchSearch);
            desc = itemView.findViewById(R.id.descSearch);
            lokasi = itemView.findViewById(R.id.alamatSearch);
            lokasiGeo = itemView.findViewById(R.id.lokasiGeoSearch);
            cuaca = itemView.findViewById(R.id.namaCuacaSearch);
            gambar = itemView.findViewById(R.id.gambarSearch);
            //String namaku = nama.getText().toString();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //System.out.println(nama.getText().toString()+" "+getPosition()+" "+nomor.getText().toString());
                    Intent i = new Intent(itemView.getContext(), preview.class);
                    i.putExtra("nama",nama.getText().toString());
                    i.putExtra("lokasiGeo",lokasiGeo.getText().toString());
                    i.putExtra("desc",desc.getText().toString());
                    i.putExtra("lokasi",lokasi.getText().toString());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }


}
