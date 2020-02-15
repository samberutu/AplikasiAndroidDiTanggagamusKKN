package com.example.ditanggamus2.model;

public class model_ListWisata {

    private String nama,gambar,desc,cuaca,lokasiGeo,lokasi;

    public model_ListWisata(){}

    public model_ListWisata(String nama, String gambar, String desc,String cuaca,String lokasiGeo,String lokasi) {
        this.nama = nama;
        this.gambar = gambar;
        this.desc = desc;
        this.cuaca = cuaca;
        this.lokasiGeo = lokasiGeo;
        this.lokasi = lokasi;
    }

    public String getCuaca() {
        return cuaca;
    }

    public void setCuaca(String cuaca) {
        this.cuaca = cuaca;
    }

    public String getLokasiGeo() {
        return lokasiGeo;
    }

    public void setLokasiGeo(String lokasiGeo) {
        this.lokasiGeo = lokasiGeo;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
