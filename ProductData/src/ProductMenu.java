import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.sql.*;

public class ProductMenu extends JFrame 
{
    public static void main(String[] args) 
    {
        // buat object window
        ProductMenu menu = new ProductMenu();

        // atur ukuran window
        menu.setSize(700, 600);

        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane((menu.mainPanel));

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    private Database database;
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JComboBox sertifikasiComboBox;
    private JLabel sertifikasiLabel;

    // constructor
    public ProductMenu()
    {

        // buat objek databse
        database = new Database();

        // isi tabel produk
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = {"???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // atur isi combo box sertifikasi
        String[] sertifikasiData = {"Halal", "SNI", "Eco-friendly", "Tidak Ada"};
        sertifikasiComboBox.setModel(new DefaultComboBoxModel<>(sertifikasiData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (selectedIndex == -1) 
                {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int confirm = JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) 
                {
                    deleteData();
                } else {
                    clearForm();
                }
            }
        });

        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mousePressed(MouseEvent e) 
            {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();

                // simpan value textfield dan combo box
                String curId = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex,2).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex,3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex,4).toString();
                String curSertifikasi = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                kategoriComboBox.setSelectedItem(curKategori);
                sertifikasiComboBox.setSelectedItem(curSertifikasi);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() 
    {
        // tentukan kolom tabel
        Object[] cols = {"no", "ID Produk", "Nama", "Harga", "Kategori", "Sertifikasi"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        // masukkan data produk ke dalam tabel
        try {
            ResultSet resultset = database.executeQuery("SELECT * FROM product ORDER BY id ASC");

            int i = 1;
            while (resultset.next()) 
            {
                Object[] row = new Object[6];
                row[0] = i++;
                row[1] = resultset.getString("id");
                row[2] = resultset.getString("nama");
                row[3] = resultset.getDouble("harga");
                row[4] = resultset.getString("kategori");
                row[5] = resultset.getString("sertifikasi");
                tmp.addRow(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tmp; // return juga harus diganti
    }

    public void insertData() 
    {
        // ambil value dari textfield dan combobox
        String id = idField.getText();
        String nama = namaField.getText();
        String hargaStr = hargaField.getText(); // simpan sementara dalam string
        String kategori = kategoriComboBox.getSelectedItem().toString();
        String sertifikasi = sertifikasiComboBox.getSelectedItem().toString();

        // validasi input kosong
        if (id.isEmpty() || nama.isEmpty() || hargaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi semua data!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaStr); // parsing setelah validasi
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // cek id duplikat
            ResultSet rs = database.executeQuery("SELECT * FROM product WHERE id='" + id + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "ID sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // tambahkan data ke dalam db
            String sqlQuery = "INSERT INTO product VALUES ('" + id + "', '" + nama + "', " + harga + ", '" + kategori + "', '" + sertifikasi + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
            System.out.println("Insert berhasil!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() 
    {
        // ambil data dari form
        String id = idField.getText();
        String nama = namaField.getText();
        String hargaStr = hargaField.getText();
        String kategori = kategoriComboBox.getSelectedItem().toString();
        String sertifikasi = sertifikasiComboBox.getSelectedItem().toString();

        // validasi form kosong
        if (id.isEmpty() || nama.isEmpty() || hargaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi semua data!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double harga;
        try {
            harga = Double.parseDouble(hargaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // ubah data produk di database
            String sqlQuery = "UPDATE product SET nama='" + nama + 
                            "', harga=" + harga + 
                            ", kategori='" + kategori + 
                            "', sertifikasi='" + sertifikasi + 
                            "' WHERE id='" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
            System.out.println("Update berhasil!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    public void deleteData() 
    {
        // hapus data
        String id = idField.getText();
        if(id.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Pilih Datanya!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            database.insertUpdateDeleteQuery("DELETE FROM product WHERE id='" + id + "'");
            productTable.setModel(setTable());
            clearForm();  
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearForm() 
    {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedItem(0);
        sertifikasiComboBox.setSelectedItem(0);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}