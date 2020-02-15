package com.example.ditanggamus2.Listener;

import com.example.ditanggamus2.model.modelPreview;
import com.example.ditanggamus2.model.model_Rekomendasi;

import java.util.List;

public interface IfirebaseDonePreview {
    void onFirebaseLoadSuccess(List<modelPreview> modelPreviews);
    void onFirebaseLoadFailed(String m);
}
