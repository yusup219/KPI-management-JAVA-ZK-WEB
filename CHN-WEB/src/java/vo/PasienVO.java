/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vo;

import java.util.Date;

/**
 *
 * @author yusup
 */
public class PasienVO {
    private int  id, umur;
    private String nama, jenis_kelamin, administrasi;
    private boolean status_pembayaran;
    private Date tanggal_lahir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAdministrasi() {
        return administrasi;
    }

    public void setAdministrasi(String administrasi) {
        this.administrasi = administrasi;
    }

    public boolean isStatus_pembayaran() {
        return status_pembayaran;
    }

    public void setStatus_pembayaran(boolean status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

  
}
