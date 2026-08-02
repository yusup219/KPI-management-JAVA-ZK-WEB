/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vo;
import java.math.BigDecimal;


/**
 *
 * @author yusup
 */
public class KendaraanVO {
   String nomor_polisi, merk, tipe;
   int id,tahun_pembuatan;
   boolean status_tersedia;
   BigDecimal harga_per_hari;

    public String getNomor_polisi() {
        return nomor_polisi;
    }

    public void setNomor_polisi(String nomor_polisi) {
        this.nomor_polisi = nomor_polisi;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTahun_pembuatan() {
        return tahun_pembuatan;
    }

    public void setTahun_pembuatan(int tahun_pembuatan) {
        this.tahun_pembuatan = tahun_pembuatan;
    }

    public boolean isStatus_tersedia() {
        return status_tersedia;
    }

    public void setStatus_tersedia(boolean status_tersedia) {
        this.status_tersedia = status_tersedia;
    }

    public BigDecimal getHarga_per_hari() {
        return harga_per_hari;
    }

    public void setHarga_per_hari(BigDecimal harga_per_hari) {
        this.harga_per_hari = harga_per_hari;
    }

    
   
}