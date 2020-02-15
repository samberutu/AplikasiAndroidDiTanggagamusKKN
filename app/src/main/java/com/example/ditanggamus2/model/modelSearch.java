package com.example.ditanggamus2.model;

public class modelSearch {

    private String nama,alamat,nomor;

    public modelSearch(){}

    public modelSearch(String nama, String alamat,String nomor) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor= nomor;
    }
}
