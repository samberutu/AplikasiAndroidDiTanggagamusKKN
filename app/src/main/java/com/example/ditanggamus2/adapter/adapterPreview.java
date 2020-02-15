package com.example.ditanggamus2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ditanggamus2.R;
import com.example.ditanggamus2.model.modelPreview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapterPreview extends PagerAdapter {


    Context context;
    List<modelPreview> list;
    LayoutInflater inflater;

    public adapterPreview(Context context, List<modelPreview> list) {
        this.context = context;
        this.list = list;
        inflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        final View view = inflater.inflate(R.layout.row_preview,container,false);
        ImageView gambar = (ImageView)view.findViewById(R.id.gambarPreview);
        Picasso.with(context).load(list.get(position).getGambar()).into(gambar);
        container.addView(view);
        return view;
    }
}
