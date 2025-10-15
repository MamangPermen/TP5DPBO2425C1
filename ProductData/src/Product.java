public class Product 
{
    private String id;
    private String nama;
    private double harga;
    private String kategori;
    private String sertifikasi;

    // Konstruktor
    public Product(String id, String nama, double harga, String kategori, String sertifikasi)
    {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.sertifikasi = sertifikasi;
    }

    // Getter dan Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setSertifikasi(String sertifikasi) {
        this.sertifikasi = sertifikasi;
    }

    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public double getHarga() {
        return this.harga;
    }

    public String getKategori() {
        return this.kategori;
    }

    public String getSertifikasi() {
        return this.sertifikasi;
    }
}