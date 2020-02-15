package com.example.ditanggamus2.model;

public class model_Rekomendasi {


    private String nama,gambar,alamat;
    public model_Rekomendasi(){};

    public model_Rekomendasi(String nama, String gambar,String alamat) {
        this.nama = nama;
        this.gambar = gambar;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
