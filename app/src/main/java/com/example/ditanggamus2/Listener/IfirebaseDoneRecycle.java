package com.example.ditanggamus2.Listener;

import com.example.ditanggamus2.model.model_ListWisata;

import java.util.List;

public interface IfirebaseDoneRecycle {
    void onFirebaseLoadSuccess(List<model_ListWisata> ListWisata);
    void onFirebaseLoadFailed(String m);
}
