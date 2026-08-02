/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import vo.UserVo;

/**
 *
 * @author yusup
 */
public class ConnectionUtil {

    private static ConnectionUtil connectionUtil;

    public static ConnectionUtil getInstance() {
        if (connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }
    
    public EntityManager em = null;

    public EntityManager getEm() {
        if (em == null || (em != null && !em.isOpen())) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ZK1_1PU");
            em = emf.createEntityManager();
        }
        return em;
    }
    public List testConnection() {
        Query q = getEm().createNativeQuery("select id, nama, alamat, umur, id_sekolah, tanggallahir, statuslulus, jeniskelamin  from siswaa order by id");
        List list = q.getResultList();
        return list;
    }

    public void testUpdate(String nama, String alamat, Integer id, Integer umur, Integer id_sekolah, Date tanggallahir, String statuslulus, String jeniskelamin) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("update siswaa set nama=?, alamat=?, umur=?, id_sekolah=?, tanggallahir=?, statuslulus=?, jeniskelamin=? where id=?");
        q.setParameter(1, nama);
        q.setParameter(2, alamat);
        q.setParameter(3, umur);
        q.setParameter(4, id_sekolah);
        q.setParameter(5, tanggallahir);
        q.setParameter(6, statuslulus);
        q.setParameter(7, jeniskelamin);
        q.setParameter(8, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testInsert(String id, String nama, String alamat, Integer umur, Integer id_sekolah, Date tanggallahir, String statuslulus, String jeniskelamin) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("insert into siswaa (id,  nama, alamat, umur, id_sekolah, tanggallahir, statuslulus, jeniskelamin ) values (?,?,?,?,?,?,?,?) ");
        q.setParameter(1, id);
        q.setParameter(2, nama);
        q.setParameter(3, alamat);
        q.setParameter(4, umur);
        q.setParameter(5, id_sekolah);
        q.setParameter(6, tanggallahir);
        q.setParameter(7, statuslulus);
        q.setParameter(8, jeniskelamin);

        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testDelete(String id) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("delete from siswaa where id=?");
        q.setParameter(1, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public List testSekolah() {
        Query q = getEm().createNativeQuery("select id_sekolah, namasekolah, alamatsekolah  from sekolah order by id_sekolah");
        List list = q.getResultList();
        return list;
    }

    public void testUpdateSekolah(String namasekolah, String alamatsekolah, Integer id_sekolah) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("update sekolah set namasekolah=?, alamatsekolah=?, id_sekolah=?");
        q.setParameter(1, namasekolah);
        q.setParameter(2, alamatsekolah);
        q.setParameter(3, id_sekolah);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testInsertSEkolah(String namasekolah, String alamatsekolah, Integer id_sekolah) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("insert into sekolah (namasekolah, alamatsekolah, id_sekolah) values (?,?,?) ");
        q.setParameter(1, namasekolah);
        q.setParameter(2, alamatsekolah);
        q.setParameter(3, id_sekolah);

        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testDeleteSekolah(Integer id_sekolah) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("delete from sekolah where id_sekolah=?");
        q.setParameter(1, id_sekolah);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public List testConnectionh() {
        Query q = getEm().createNativeQuery("select id, nama, umur, jenis_kelamin, administrasi, status_pembayaran, tanggal_lahir  from Pasien order by id");
        List list = q.getResultList();
        return list;
    }

    public void testTambahDP(Integer id, String nama, Integer umur, String jenis_kelamin, String administrasi, Boolean status_pembayaran, Date tanggal_lahir) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("insert into Pasien (id,  nama, umur,jenis_kelamin, administrasi, status_pembayaran, tanggal_lahir ) values (?,?,?,?,?,?,?) ");
        q.setParameter(1, id);
        q.setParameter(2, nama);
        q.setParameter(3, umur);
        q.setParameter(4, jenis_kelamin);
        q.setParameter(5, administrasi);
        q.setParameter(6, status_pembayaran);
        q.setParameter(7, tanggal_lahir);

        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testDeleteDP(Integer id) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("delete from Pasien where id=?");
        q.setParameter(1, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void UpdateDP(String nama, Integer umur, String jenis_kelamin, String administrasi, Boolean status_pembayaran, Date tanggal_lahir, Integer id) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("update Pasien set  nama=?, umur=?, jenis_kelamin=?,  administrasi=?,  status_pembayaran=?, tanggal_lahir=? where id=?");

        q.setParameter(1, nama);
        q.setParameter(2, umur);
        q.setParameter(3, jenis_kelamin);
        q.setParameter(4, administrasi);
        q.setParameter(5, status_pembayaran);
        q.setParameter(6, tanggal_lahir);
        q.setParameter(7, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

//    Koneksi USER
    public List testConnection123() {
        EntityManager em = getEm();
        List list = new java.util.ArrayList();
        try {
            String sql = "SELECT nama_user, password FROM tbuser";
            Query q = em.createNativeQuery(sql);
            list = q.getResultList();
        } catch (Exception e) {
            System.out.println("DEBUG ERROR DATABASE: Terjadi kendala saat menarik data dari tbuser!");
            e.printStackTrace();
        }
        return list;
    }

    public void testInsertUser(String idUser, String namaUser, String firstName, String lastName, String password, String noTlp) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();

            String sql = "INSERT INTO tbuser (id_user, nama_user, first_name, last_name, password, no_tlp) VALUES (?, ?, ?, ?, crypt(?, gen_salt('bf')), ?)";
            Query q = em.createNativeQuery(sql);

            q.setParameter(1, idUser);
            q.setParameter(2, namaUser);
            q.setParameter(3, firstName);
            q.setParameter(4, lastName);
            q.setParameter(5, password);
            q.setParameter(6, noTlp);

            q.executeUpdate();
            em.getTransaction().commit();
            System.out.println("DEBUG: Berhasil menyimpan user baru (TERENKRIPSI) ke tbuser!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public List getDataUser() {
        EntityManager em = getEm();
        List list = new java.util.ArrayList();
        try {
            String sql = "SELECT id_user, nama_user, first_name, last_name, password, no_tlp FROM tbuser ORDER BY id_user ASC";
            Query q = em.createNativeQuery(sql);
            list = q.getResultList();
        } catch (Exception e) {
            System.out.println("DEBUG ERROR DATABASE: Gagal mengambil data user!");
            e.printStackTrace();
        }
        return list;
    }

    public void deleteUser(String idUser) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("DELETE FROM tbuser WHERE id_user=?");
            q.setParameter(1, idUser);
            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public void insertUser(String idUser, String namaUser, String firstName, String lastName, String password, String noTlp) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        try {
            String sql = "INSERT INTO tbuser (id_user, nama_user, first_name, last_name, password, no_tlp) VALUES (?, ?, ?, ?, crypt(?, gen_salt('bf')), ?)";
            Query q = em.createNativeQuery(sql);

            q.setParameter(1, idUser);
            q.setParameter(2, namaUser);
            q.setParameter(3, firstName);
            q.setParameter(4, lastName);
            q.setParameter(5, password);
            q.setParameter(6, noTlp);

            q.executeUpdate();
            em.getTransaction().commit();
            System.out.println("DEBUG: Berhasil menyimpan user baru!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public void updateUser(String idUser, String namaUser, String firstName, String lastName, String password, String noTlp) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        try {
            String sql = "UPDATE tbuser SET nama_user = ?, first_name = ?, last_name = ?, password = crypt(?, gen_salt('bf')), no_tlp = ? WHERE id_user = ?";
            Query q = em.createNativeQuery(sql);

            q.setParameter(1, namaUser);
            q.setParameter(2, firstName);
            q.setParameter(3, lastName);
            q.setParameter(4, password);
            q.setParameter(5, noTlp);
            q.setParameter(6, idUser);

            q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public void testInsertPengguna(String nama, String pass) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("INSERT INTO loogin (nama, pass) VALUES (?, ?)");
        q.setParameter(1, nama);
        q.setParameter(2, pass);

        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void updatePassword(String username, String passBaru) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            String sql = "UPDATE tbuser SET password = crypt(?, gen_salt('bf')) WHERE nama_user = ?";
            Query q = em.createNativeQuery(sql);
            q.setParameter(1, passBaru);
            q.setParameter(2, username);

            q.executeUpdate();
            em.getTransaction().commit();
            System.out.println("DEBUG: Password sukses di-update dan di-hash untuk user: " + username);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public List loginCheckSecure(String usernameInput, String passwordInput) {
        EntityManager em = getEm();
        List list = new java.util.ArrayList();
        try {
            String sql = "SELECT nama_user FROM tbuser WHERE nama_user = ?1 AND password = crypt(?2, password)";

            Query q = em.createNativeQuery(sql);
            q.setParameter(1, usernameInput);
            q.setParameter(2, passwordInput);

            list = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//     Konekis KPI 
    public List testConnectionKpi(String keyword, java.util.Date tglAwal, java.util.Date tglAkhir) {
        EntityManager em = getEm();
        
        String sql = "SELECT p.id, p.nama, 20 AS hadir_target, COALESCE(k.hadir_aktual, 0) AS hadir_aktual, 100 AS bobot, k.tanggal_penilaian "
                + "FROM pegawai1 p "
                + "LEFT JOIN tb_kpi k ON p.id = k.nama_pegawai WHERE 1=1";

        boolean hasKeyword = (keyword != null && !keyword.trim().isEmpty());
        boolean hasDates = (tglAwal != null && tglAkhir != null);

        if (hasKeyword) {
            sql += " AND LOWER(p.nama) LIKE ?";
        }
        
        if (hasDates) {
            sql += " AND k.tanggal_penilaian BETWEEN ? AND ?";
        }
        
        sql += " ORDER BY p.id ASC";

        Query q = em.createNativeQuery(sql);
        
        int paramIdx = 1;
        if (hasKeyword) {
            q.setParameter(paramIdx++, "%" + keyword.toLowerCase() + "%");
        }
        if (hasDates) {
            q.setParameter(paramIdx++, tglAwal);
            q.setParameter(paramIdx++, tglAkhir);
        }

        return q.getResultList();
    }

    public void testUpdateHadirKpi(String idPegawai, Integer hadirAktual, java.util.Date tanggalBaru) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            String cekSql = "SELECT COUNT(*) FROM tb_kpi WHERE nama_pegawai = ?";
            Query qCek = em.createNativeQuery(cekSql);
            qCek.setParameter(1, idPegawai);
            Long count = ((Number) qCek.getSingleResult()).longValue();

            String sql;
            if (count > 0) {
                sql = "UPDATE tb_kpi SET hadir_aktual = ?, tanggal_penilaian = ? WHERE nama_pegawai = ?";
                Query q = em.createNativeQuery(sql);
                q.setParameter(1, hadirAktual);
                q.setParameter(2, tanggalBaru);
                q.setParameter(3, idPegawai);
                q.executeUpdate();
            } else {
                sql = "INSERT INTO tb_kpi (nama_pegawai, hadir_target, hadir_aktual, bobot, tanggal_penilaian) VALUES (?, 20, ?, 100, ?)";
                Query q = em.createNativeQuery(sql);
                q.setParameter(1, idPegawai);
                q.setParameter(2, hadirAktual);
                q.setParameter(3, tanggalBaru);
                q.executeUpdate();
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

//     Koneksi PEGAWAI
    public List testConnectionPegawai1() {
        String sql = "SELECT p.id, p.nama, p.alamat, p.umur, d.departemen, p.tanggallahir, p.gender "
                + "FROM pegawai1 p "
                + "LEFT JOIN departemen d ON p.id_departemen = d.id_departemen "
                + "ORDER BY p.id";
        Query q = getEm().createNativeQuery(sql);
        List list = q.getResultList();
        return list;
    }

    public void testInsertPegawai1(String id, String nama, String alamat, Integer umur, String id_departemen, Date tanggal_lahir, String gender) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("insert into pegawai1 (id, nama, alamat, umur, id_departemen, tanggallahir, gender) values (?,?,?,?,?,?,?)");
        q.setParameter(1, id);
        q.setParameter(2, nama);
        q.setParameter(3, alamat);
        q.setParameter(4, umur);
        q.setParameter(5, id_departemen);
        q.setParameter(6, tanggal_lahir);
        q.setParameter(7, gender);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testUpdatePegawai1(String nama, String alamat, String id, Integer umur, String id_departemen, Date tanggal_lahir, String gender) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("update pegawai1 set nama=?, alamat=?, umur=?, id_departemen=?, tanggallahir=?, gender=? where id=?");
        q.setParameter(1, nama);
        q.setParameter(2, alamat);
        q.setParameter(3, umur);
        q.setParameter(4, id_departemen);
        q.setParameter(5, tanggal_lahir);
        q.setParameter(6, gender);
        q.setParameter(7, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testDeletePegawai1(String id) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("delete from pegawai1 where id=?");
        q.setParameter(1, id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

//     Koneksi DEPARTEMEN 
    public List testDepartemen() {
        Query q = getEm().createNativeQuery("select id_departemen, departemen, kepala_departemen from departemen order by id_departemen");
        List list = q.getResultList();
        return list;
    }

    public void testInsertDepartemen(String id_departemen, String departemen, String kepala_departemen) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("insert into departemen (id_departemen, departemen, kepala_departemen) values (?,?,?)");
        q.setParameter(1, id_departemen);
        q.setParameter(2, departemen);
        q.setParameter(3, kepala_departemen);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testUpdateDepartemen(String departemen, String kepala_departemen, String id_departemen) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("update departemen set departemen=?, kepala_departemen=? where id_departemen=?");
        q.setParameter(1, departemen);
        q.setParameter(2, kepala_departemen);
        q.setParameter(3, id_departemen);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void testDeleteDepartemen(String id_departemen) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = getEm().createNativeQuery("delete from departemen where id_departemen=?");
        q.setParameter(1, id_departemen);
        q.executeUpdate();
        em.getTransaction().commit();
    }
}