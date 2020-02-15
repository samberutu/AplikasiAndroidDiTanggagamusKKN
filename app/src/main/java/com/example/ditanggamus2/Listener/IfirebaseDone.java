package com.example.ditanggamus2.Listener;

import com.example.ditanggamus2.model.model_ListWisata;

import java.util.List;

public interface IfirebaseDone {
    void onFirebaseLoadSuccess(List<model_ListWisata> rekomendasiList);
    void onFirebaseLoadFailed(String m);


}
