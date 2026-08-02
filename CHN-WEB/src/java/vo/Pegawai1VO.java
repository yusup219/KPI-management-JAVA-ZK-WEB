package vo;
import java.util.Date;
/**
 *
 * @author yusup
 */
public class Pegawai1VO {
    
    private String id, id_departemen;
    private int umur;
    private String nama, alamat, gender; 
    private String nama_departemen; // Untuk menampung nama departemen hasil JOIN database
    private Date tanggal_lahir;

    // Getter & Setter 
    public int getUmur() {
        return umur;
    }
    public void setUmur(int umur) {
        this.umur = umur;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId_departemen() {
        return id_departemen;
    }
    public void setId_departemen(String id_departemen) {
        this.id_departemen = id_departemen;
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
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }
    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }
    public String getNama_departemen() {
        return nama_departemen;
    }
    public void setNama_departemen(String nama_departemen) {
        this.nama_departemen = nama_departemen;
    } 
}