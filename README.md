# TP5DPBO2425C

## 🤝🏻 Janji

Saya Nadhif Arva Anargya dengan NIM 2404336 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek. Untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

---

## 🎨 Desain Program

Program ini dirancang menggunakan **Java Swing** dan terhubung langsung dengan **database MySQL**.  
Konsep yang digunakan tetap berorientasi objek (**OOP**), namun proses penyimpanan data kini dilakukan secara **real-time ke database**, bukan lagi menggunakan `ArrayList` seperti pada TP sebelumnya.

Berikut struktur kelas utama dalam program:

1. **Product.java**  
   Kelas ini berfungsi sebagai **model data produk**.  
   Atribut yang digunakan:
   - `id` → kode unik produk  
   - `name` → nama produk  
   - `price` → harga produk  
   - `quantity` → jumlah stok produk
   - `sertifikasi` → sertifikat dari produk

   Kelas ini memiliki **getter**, **setter**, dan **konstruktor** untuk mengatur nilai setiap atribut serta mempermudah pengolahan data produk.

2. **Database.java**  
   Kelas ini berfungsi untuk **mengatur koneksi ke database MySQL**.  
   Tugas utamanya:
   - Membuka koneksi ke database `db_product`.  
   - Menyediakan method untuk mengeksekusi perintah **SELECT, INSERT, UPDATE,** dan **DELETE**.  
   - Menangani error koneksi agar program tetap stabil.
  
     ### Struktur Tabel
      | Kolom | Tipe Data | Keterangan |
      |--------|------------|------------|
      | `id` | VARCHAR(10) | Primary Key |
      | `nama` | VARCHAR(100) | Nama produk |
      | `harga` | DOUBLE | Harga produk |
      | `kategori` | VARCHAR(50) | Kategori produk |
      | `sertifikasi` | VARCHAR(50) | Sertifikasi produk |

3. **ProductMenu.java**  
   Kelas ini merupakan **tampilan utama aplikasi (GUI)** yang dibuat menggunakan Java Swing Form.  
   Komponen utama yang digunakan:
   - `JTextField` → untuk input ID, nama, dan harga produk.  
   - `JComboBox` → untuk memilih kategori dan sertifikasi produk.  
   - `JButton` → untuk aksi **Add**, **Update**, **Delete**, dan **Cancel**.  
   - `JTable` → untuk menampilkan seluruh data produk dari database.  
   - `JLabel` dan `JPanel` → sebagai elemen pendukung tampilan GUI.

   Di kelas ini juga terdapat semua **logika CRUD (Create, Read, Update, Delete)** serta **event listener** untuk menangani aksi pengguna.

   ![alt text](https://github.com/MamangPermen/TP5DPBO2425C1/blob/main/Dokumentasi/DesignUI.JPG)

---

## 🔄 Penjelasan Alur Program

1. **Tampilan Utama**  
   Saat program dijalankan, jendela utama akan menampilkan form input data produk di bagian atas dan tabel data di bagian bawah.  
   Data pada tabel otomatis diambil dari database dan ditampilkan dalam format kolom:  
   **No, ID Produk, Nama, Harga, Kategori, Sertifikasi.**  
   Tombol **Add** aktif secara default, sementara tombol **Delete** disembunyikan hingga pengguna memilih data.

2. **Menambahkan Data Produk Baru**  
   Pengguna dapat menambahkan produk baru dengan mengisi semua kolom form input, lalu menekan tombol **Add**.  
   Program akan memeriksa:
   - Apakah semua field sudah terisi.
   - Apakah harga valid? (Bukan huruf)
   - Apakah ID produk sudah ada di database.  
   Jika valid, data akan disimpan ke database dan langsung muncul pada tabel.  
   Setelah berhasil, form akan dikosongkan dan pesan notifikasi ditampilkan.

3. **Menampilkan Data Produk (Read)**  
   Semua data produk yang tersimpan di database akan muncul di tabel pada tampilan utama.  
   Pengguna dapat melihat seluruh daftar produk secara langsung.  
   Jika pengguna mengklik salah satu baris tabel, data dari baris tersebut akan otomatis muncul di form input,  
   tombol **Add** berubah menjadi **Update**, dan tombol **Delete** akan ditampilkan.

4. **Memperbarui Data Produk (Update)**  
   Saat pengguna memilih data pada tabel, form input akan terisi otomatis.  
   Pengguna dapat melakukan perubahan data, lalu menekan tombol **Update**.  
   Program kemudian memperbarui data produk tersebut di database dan memperbarui tabel di GUI.  
   Setelah proses selesai, form dikosongkan dan tombol kembali ke mode semula.

5. **Menghapus Data Produk (Delete)**  
   Untuk menghapus data, pengguna dapat memilih baris produk di tabel, lalu menekan tombol **Delete**.  
   Program akan menampilkan dialog konfirmasi:  
   *“Hapus data??”*  
   Jika pengguna memilih **Ya**, data akan dihapus dari database dan tabel diperbarui secara otomatis.

6. **Membersihkan Form Input (Cancel)**  
   Jika pengguna ingin mengosongkan field form atau membatalkan aksi sebelumnya, klik tombol **Cancel**.  
   Semua kolom input akan kembali kosong, tombol **Add** aktif kembali, dan tombol **Update/Delete** dinonaktifkan.

---

## ⚙️ Fitur Validasi

Program ini memiliki beberapa fitur validasi untuk memastikan data yang dimasukkan benar, yaitu:
- Jika ada field yang kosong → tampil pesan **“Isi semua data!”**  
- Jika harga bukan angka → tampil pesan **“Harga harus berupa angka!”**  
- Jika ID sudah terdaftar → tampil pesan **“ID sudah terdaftar!”**  
- Semua pesan kesalahan ditampilkan menggunakan **JOptionPane** agar interaktif dan mudah dipahami pengguna.

---

## 📸 Dokumentasi

![alt text](https://github.com/MamangPermen/TP5DPBO2425C1/blob/main/Dokumentasi/RecordProgram.gif)
