package com.sertifikasi.simpleapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "barang")
public class Barang {

    @Id
    private int kodeBarang;
    private String namaBarang;
    private int jumlah;
    private int harga;
    
    public Barang() {
        
    }

    public int getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(int kodeBarang) {
        if (kodeBarang > 0) {
            this.kodeBarang = kodeBarang;
        } else {
            throw new IllegalArgumentException("Invalid kodeBarang value");
        }
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        if (jumlah >= 0) {
            this.jumlah = jumlah;
        } else {
            throw new IllegalArgumentException("Invalid jumlah value");
        }
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        if (harga >= 0) {
            this.harga = harga;
        } else {
            throw new IllegalArgumentException("Invalid harga value");
        }
    }

    
    public String toString() {
        return "Barang [kodeBarang=" + kodeBarang + ", namaBarang=" + namaBarang + ", jumlah=" + jumlah + ", harga=" + harga + "]";
    }
}
