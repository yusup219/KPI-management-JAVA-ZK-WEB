create view view_hasil_service as
select 
p.nama_pelanggan,
p.umur,
d.nama_montir,
d.spesialis,
o.nama_barang,
e.catatan_pelanggan,
e.tanggal
from hasil_servis e
join pelanggan p on p.id_pelanggan = e.id_pelanggan
join montir d on d.id_montir = e.id_montir
join barang o on o.id_barang = e.id_barang;

create view view_pegawai1 as
select 
p.nama AS "Nama Pegawai",
p.alamat AS "Alamat",
p.umur AS "Umur",
p.tanggallahir AS "Tanggal Lahir",
p.gender AS "Gender",
d.departemen AS "Nama Departemen",
d.kepala_departemen AS "Kepala Departemen"
from pegawai1 p
join departemen d on d.id_departemen = p.id_departemen;

create view view_akademik as
 select
mhs.nama as "Nama Mahasiswa",
mk.nama_mk as "Mata Kuliah",
ab.setus_hadir as "Status Hadir",
n.nilai_akhir as "Nilai Akhir",
n.gred as "Gred",
pm.status_bayar as "Status Pembayaran"
from 
select mhs.mahasiswa as
mhs.nama as "Nama Mahasiswa",
mk.nama_mk as "Mata Kuliah",
ab.setus_hadir as "Status Hadir",
n.nilai_akhir as "Nilai Akhir",
n.gred as "Gred",
pm.status_bayar as "Status Pembayaran"
from mahasiswa mhs 
union
select mk.matakuliah as
mhs.nama as "Nama Mahasiswa",
mk.nama_mk as "Mata Kuliah",
ab.setus_hadir as "Status Hadir",
n.nilai_akhir as "Nilai Akhir",
n.gred as "Gred",
pm.status_bayar as "Status Pembayaran"
from matakuliah mk
union 
select ab absensi as
mhs.nama as "Nama Mahasiswa",
mk.nama_mk as "Mata Kuliah",
ab.setus_hadir as "Status Hadir",
n.nilai_akhir as "Nilai Akhir",
n.gred as "Gred",
pm.status_bayar as "Status Pembayaran"
from absensi ab 
union 
select n nilai as
mhs.nama as "Nama Mahasiswa",
mk.nama_mk as "Mata Kuliah",
ab.setus_hadir as "Status Hadir",
n.nilai_akhir as "Nilai Akhir",
n.gred as "Gred",
pm.status_bayar as "Status Pembayaran"
from nilai n 
union 
select pm pembayaran as
mhs.nama as "Nama Mahasiswa",
mk.nama_mk as "Mata Kuliah",
ab.setus_hadir as "Status Hadir",
n.nilai_akhir as "Nilai Akhir",
n.gred as "Gred",
pm.status_bayar as "Status Pembayaran"
from pemabaran as pm

--task 1
create view view_matkul as
select 
m.nama,
mk.nama_mk
from mahasiswa m
inner join krs k on k.id_mhs = m.id_mhs
inner join matakuliah mk on mk.id_mk = k.id_mk;

--task 2
create view view_absen as
select  
    m.nama AS nama_mahasiswa,
    a.status_hadir
FROM mahasiswa m
LEFT JOIN absensi a ON  a.id_mhs = m.id_mhs;

--task 3
CREATE OR REPLACE VIEW  view_role as
select 
mahasiswa.nama AS nama,
'Mahasiswa'::text AS role
from mahasiswa
union 
select 
dosen.nama AS nama,
'Dosen'::text AS role
from dosen;

--task 4
create OR REPLACE view akademi as
select
m.nama,
mk.nama_mk,
n.nilai_akhir,
n.grade,
pm.status_bayar
from mahasiswa m
inner join nilai n on n.id_mhs = m.id_mhs
inner join matakuliah mk on mk.id_mk = n.id_mk
left join pembayaran pm on pm.id_mhs = m.id_mhs;



---DATABASE UNIVERSITAS
CREATE MATERIALIZED VIEW mv_ipk_mahasiswa AS
SELECT
    m.id_mhs,
    m.nama,
    AVG(n.nilai_akhir) AS ipk_rata_rata,
    COUNT(n.id_mk) AS total_mk
FROM mahasiswa m
JOIN nilai n ON m.id_mhs = n.id_mhs
GROUP BY m.id_mhs, m.nama;

CREATE MATERIALIZED VIEW mv_kehadiran AS
SELECT
    m.id_mhs,
    m.nama,
    COUNT(a.id_absensi) AS total_pertemuan,
    SUM(CASE WHEN a.status_hadir = 'Hadir' THEN 1 ELSE 0 END) AS hadir
FROM mahasiswa m
JOIN absensi a ON m.id_mhs = a.id_mhs
GROUP BY m.id_mhs, m.nama;
