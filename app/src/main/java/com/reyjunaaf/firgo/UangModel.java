package com.reyjunaaf.firgo;

public class UangModel {
    private String nama, jenis, jumlah, note, date ,id;

    public UangModel(String nama, String jenis, String jumlah, String note, String date, String id) {
        this.nama = nama;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.note = note;
        this.date = date;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {return jenis;}

    public String getJumlah() {
        return jumlah;
    }

    public String getnote() {return note;}

    public String getDate() {return date;}

    public String getId() {return id;}
}

