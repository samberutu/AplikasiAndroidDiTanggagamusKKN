package com.example.ditanggamus2.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ditanggamus2.MainActivity;
import com.example.ditanggamus2.R;
import com.example.ditanggamus2.model.model_ListWisata;
import com.example.ditanggamus2.preview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapterListWisata extends RecyclerView.Adapter<adapterListWisata.MyViewHolder> {

    ArrayList<model_ListWisata> list;
    Context context;

    public adapterListWisata(ArrayList<model_ListWisata> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_wisata_tanggamus,parent,false);

        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nama.setText(list.get(position).getNama());
        holder.desc.setText(list.get(position).getDesc());
        holder.lokasiGeo.setText(list.get(position).getLokasiGeo());
        holder.lokasi.setText(list.get(position).getLokasi());
        holder.cuaca.setText(list.get(position).getCuaca());

        Picasso.with(context).load(list.get(position).getGambar()).into(holder.gambar);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nama,desc,cuaca,lokasiGeo,lokasi;
        ImageView gambar;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.NamaListWisata);
            desc = itemView.findViewById(R.id.descListWisata);
            lokasiGeo = itemView.findViewById(R.id.lokasiGeo);
            lokasi = itemView.findViewById(R.id.alamatListWisata);
            cuaca = itemView.findViewById(R.id.namaCuaca);
            gambar = itemView.findViewById(R.id.gambarListWisata);
            //String namaku = nama.getText().toString();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(nama.getText().toString()+" "+getPosition()+" "+lokasi.getText().toString());
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
