package view;
import dao.PesertaDAO;
import model.Peserta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.sql.SQLException;

public class FormPendaftaranKursus extends javax.swing.JFrame {

    private final Color warnaLatar = new Color(248, 250, 252);
    private final Color warnaHeader = new Color(0, 102, 204);
    private final Color warnaProses = new Color(0, 102, 153);
    private final Color warnaReset = new Color(255, 51, 51);
    private final PesertaDAO dao = new PesertaDAO();
    private int selectedId = -1;
    
    private JPanel panelUtama;
    private JPanel panelHeader;
    private JPanel panelForm;
    private JPanel panelJenis;
    private JPanel panelFasilitas;
    private JPanel panelTombol;

    private JLabel lblJudul;
    private JLabel lblNama;
    private JLabel lblKodeAkses;
    private JLabel lblJenis;
    private JLabel lblKursus;
    private JLabel lblFasilitas;
    private JLabel lblRingkasan;
    private JScrollPane scrollRingkasan;

    
public FormPendaftaranKursus() {
    initComponents();

    configureTable();
loadTable();
clearForm();

tblPeserta.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int row = tblPeserta.getSelectedRow();

        if (row == -1) {
            return;
        }

        selectedId = Integer.parseInt(
            tblPeserta.getValueAt(row, 0).toString()
        );

        txtNim.setText(
            tblPeserta.getValueAt(row, 1).toString()
        );

        txtNama.setText(
            tblPeserta.getValueAt(row, 2).toString()
        );

        String jk = tblPeserta.getValueAt(row, 3).toString();

        rbLaki.setSelected("L".equals(jk));
        rbPerempuan.setSelected("P".equals(jk));

        txtProdi.setText(
            tblPeserta.getValueAt(row, 4).toString()
        );

        txtNoHp.setText(
            tblPeserta.getValueAt(row, 5).toString()
        );

        txtEmail.setText(
            tblPeserta.getValueAt(row, 6).toString()
        );

        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }
});

    groupJenis.add(rbMahasiswa);
    groupJenis.add(rbUmum);

    txtRingkasan.setEditable(false);
    txtRingkasan.setLineWrap(false);

    getRootPane().setDefaultButton(btnSimpan);

    setMinimumSize(new Dimension(760, 650));
    setSize(new Dimension(920, 950));
    setLocationRelativeTo(null);
}

    
     private void addRow(int row, JLabel label, java.awt.Component component, double weightY) {
        GridBagConstraints cLabel = new GridBagConstraints();
        cLabel.gridx = 0;
        cLabel.gridy = row;
        cLabel.anchor = GridBagConstraints.NORTHWEST;
        cLabel.insets = new Insets(9, 0, 11, 16);
        cLabel.ipadx = 18;
        panelForm.add(label, cLabel);

        GridBagConstraints cField = new GridBagConstraints();
        cField.gridx = 1;
        cField.gridy = row;
        cField.weightx = 1.0;
        cField.weighty = weightY;
        cField.fill = weightY > 0 ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;
        cField.anchor = GridBagConstraints.NORTHWEST;
        cField.insets = new Insets(0, 0, 11, 0);
        panelForm.add(component, cField);
    }
     
    private String getJenisPeserta() {
        if (rbMahasiswa.isSelected()) {
            return "Mahasiswa";
        }
        if (rbUmum.isSelected()) {
            return "Umum";
        }
        return "";
    }
    
    private double hitungBiayaDasar(String kursus) {
        switch (kursus) {
            case "Java Desktop":
                return 500_000;
            case "Data Science":
                return 650_000;
            case "Web Programming":
                return 450_000;
            case "UI/UX Design":
                return 400_000;
            default:
                return 0;
        }
    }
    
    private double hitungBiayaFasilitas() {
        double total = 0;
        if (cbModul.isSelected()) {
            total += 50_000;
        }
        if (cbSertifikat.isSelected()) {
            total += 75_000;
        }
        if (cbRekaman.isSelected()) {
            total += 40_000;
        }
        return total;
    }

    private String buatRingkasanFasilitas() {
        String[] fasilitasDipilih = new String[3];
        int jumlah = 0;

        if (cbModul.isSelected()) {
            fasilitasDipilih[jumlah] = "Modul Cetak";
            jumlah++;
        }
        if (cbSertifikat.isSelected()) {
            fasilitasDipilih[jumlah] = "Sertifikat";
            jumlah++;
        }
        if (cbRekaman.isSelected()) {
            fasilitasDipilih[jumlah] = "Akses Rekaman";
            jumlah++;
        }

        if (jumlah == 0) {
            return "- Tidak ada fasilitas tambahan\n";
        }

        StringBuilder hasil = new StringBuilder();
        for (int i = 0; i < jumlah; i++) {
            hasil.append("- ").append(fasilitasDipilih[i]).append("\n");
        }
        return hasil.toString();
    }
    
    private boolean validasiInput() {
        String nama = txtNama.getText().trim();
        String kode = new String(txtKodeAkses.getPassword());

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama peserta wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return false;
        }

        if (!kode.equals("1234")) {
            JOptionPane.showMessageDialog(this,
                    "Kode akses demo salah.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtKodeAkses.requestFocus();
            return false;
        }

        if (!rbMahasiswa.isSelected() && !rbUmum.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Pilih jenis peserta.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Pilih kursus terlebih dahulu.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            cmbKursus.requestFocus();
            return false;
        }

        return true;
    }
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupJenis = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtKodeAkses = new javax.swing.JPasswordField();
        rbMahasiswa = new javax.swing.JRadioButton();
        rbUmum = new javax.swing.JRadioButton();
        cmbKursus = new javax.swing.JComboBox<>();
        cbModul = new javax.swing.JCheckBox();
        cbSertifikat = new javax.swing.JCheckBox();
        cbRekaman = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRingkasan = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNim = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rbLaki = new javax.swing.JRadioButton();
        rbPerempuan = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtProdi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPeserta = new javax.swing.JTable();
        btnProses = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jLabel1.setText("DATA PESERTA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 730, -1, -1));
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 440, -1));
        getContentPane().add(txtKodeAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 440, -1));

        groupJenis.add(rbMahasiswa);
        rbMahasiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbMahasiswa.setText("Mahasiswa");
        getContentPane().add(rbMahasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        groupJenis.add(rbUmum);
        rbUmum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbUmum.setText("Umum");
        getContentPane().add(rbUmum, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        cmbKursus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Data Science", "UI/UX" }));
        getContentPane().add(cmbKursus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        cbModul.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbModul.setText("Modul Cetak");
        getContentPane().add(cbModul, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, -1, -1));

        cbSertifikat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbSertifikat.setText("Sertifikat");
        getContentPane().add(cbSertifikat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        cbRekaman.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbRekaman.setText("Akses Rekaman");
        getContentPane().add(cbRekaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, -1, -1));

        txtRingkasan.setColumns(20);
        txtRingkasan.setRows(5);
        jScrollPane1.setViewportView(txtRingkasan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 440, 260));

        btnSimpan.setBackground(new java.awt.Color(0, 0, 0));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSimpan.setText("Simpan ke database");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 680, 150, -1));

        btnReset.setBackground(new java.awt.Color(153, 153, 153));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 680, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Kode Akses");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("NIM");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Kursus");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Jenis Peserta");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fasilitas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Pencarian");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 770, -1, -1));

        txtNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNimActionPerformed(evt);
            }
        });
        getContentPane().add(txtNim, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 440, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        buttonGroup3.add(rbLaki);
        rbLaki.setText("Laki-laki");
        getContentPane().add(rbLaki, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        buttonGroup3.add(rbPerempuan);
        rbPerempuan.setText("Perempuan");
        rbPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPerempuanActionPerformed(evt);
            }
        });
        getContentPane().add(rbPerempuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Nama Peserta");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));
        getContentPane().add(txtProdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 440, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Jenis Kelamin");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        getContentPane().add(txtNoHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 440, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Program Studi");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 440, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Nomor HP");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        btnHapus.setBackground(new java.awt.Color(255, 0, 51));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, -1, -1));

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        tblPeserta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblPeserta);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 810, 440, 230));

        btnProses.setBackground(new java.awt.Color(0, 204, 51));
        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProses.setText("Proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        getContentPane().add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 640, -1, -1));

        btnUbah.setBackground(new java.awt.Color(0, 51, 255));
        btnUbah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        getContentPane().add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 640, 70, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jLabel13.setText("FORM PENDAFTARAN KURSUS");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Ringkasan");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 770, 160, -1));

        btnCari.setForeground(new java.awt.Color(51, 51, 255));
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        getContentPane().add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 770, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

    String nim = txtNim.getText().trim();
    String nama = txtNama.getText().trim();
    String prodi = txtProdi.getText().trim();
    String noHp = txtNoHp.getText().trim();
    String email = txtEmail.getText().trim();

    String jenisKelamin = "";

    if (rbLaki.isSelected()) {
        jenisKelamin = "L";
    } else if (rbPerempuan.isSelected()) {
        jenisKelamin = "P";
    }

    // Validasi input wajib
    if (nim.isEmpty() || nama.isEmpty()
            || prodi.isEmpty() || jenisKelamin.isEmpty()) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "NIM, nama, jenis kelamin, dan prodi wajib diisi.",
                "Validasi",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    
    // Membuat objek peserta
    model.Peserta peserta = new model.Peserta(
        0,              // ID awal, sesuaikan dengan pengelolaan ID database
        nama,
        noHp,
        nim,
        jenisKelamin,
        prodi,
        email
    );

    // Menyimpan data ke database
    PesertaDAO dao = new PesertaDAO();

    try {
        boolean berhasil = dao.insert(peserta);

        if (berhasil) {
            JOptionPane.showMessageDialog(
                this,
                "Data peserta berhasil disimpan.",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();

            // Refresh tabel setelah menyimpan
            loadTable();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Data peserta tidak berhasil disimpan.",
                "Informasi",
                JOptionPane.WARNING_MESSAGE
            );
        }
    } catch (java.sql.SQLException e) {
        JOptionPane.showMessageDialog(
            this,
            "Gagal menyimpan data:\n" + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }



        String jenis = getJenisPeserta();
        String kursus = cmbKursus.getSelectedItem().toString();

        double biayaDasar = hitungBiayaDasar(kursus);
        double diskon = jenis.equals("Mahasiswa") ? biayaDasar * 0.10 : 0;
        double biayaFasilitas = hitungBiayaFasilitas();
        double total = biayaDasar - diskon + biayaFasilitas;

        String fasilitas = buatRingkasanFasilitas();

        String ringkasan =
                "PENDAFTARAN KURSUS\n"
                + "================================\n"
                + "Nama            : " + nama + "\n"
                + "Jenis Peserta   : " + jenis + "\n"
                + "Kursus          : " + kursus + "\n"
                + "Biaya Dasar     : " + formatRupiah(biayaDasar) + "\n"
                + "Diskon          : " + formatRupiah(diskon) + "\n"
                + "Fasilitas:\n" + fasilitas
                + "Biaya Fasilitas : " + formatRupiah(biayaFasilitas) + "\n"
                + "================================\n"
                + "TOTAL           : " + formatRupiah(total);

        txtRingkasan.setText(ringkasan);
        txtRingkasan.setCaretPosition(0);

        JOptionPane.showMessageDialog(this,
                "Pendaftaran berhasil diproses.",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE);
    // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        int jawab = JOptionPane.showConfirmDialog(
                this,
                "Kosongkan seluruh input?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION);

        if (jawab == JOptionPane.YES_OPTION) {
            resetForm();
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNimActionPerformed

    private void rbPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPerempuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPerempuanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (selectedId <= 0) {
        JOptionPane.showMessageDialog(
        this,
        "Pilih data peserta yang akan dihapus."
        );
        return;
        }
        String nama = txtNama.getText().trim();
        int jawab = JOptionPane.showConfirmDialog(
        this,
        "Yakin menghapus peserta: " + nama + "?",
        "Konfirmasi Hapus",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
        );
        if (jawab != JOptionPane.YES_OPTION) {
        return;
        }
        try {
        if (dao.delete(selectedId)) {
        JOptionPane.showMessageDialog(
        this,
        "Data peserta berhasil dihapus."
        );
        refreshData();
        }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
        this,
        "Gagal menghapus data: " + ex.getMessage(),
        "Database Error",
        JOptionPane.ERROR_MESSAGE
        );
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:
        int row = tblPeserta.getSelectedRow();
        if (row == -1) {
        return;
        }
        selectedId = Integer.parseInt(
        tblPeserta.getValueAt(row, 0).toString()
        );
        txtNim.setText(tblPeserta.getValueAt(row, 1).toString());
        txtNama.setText(tblPeserta.getValueAt(row, 2).toString());
        String jk = tblPeserta.getValueAt(row, 3).toString();
        rbLaki.setSelected("L".equals(jk));
        rbPerempuan.setSelected("P".equals(jk));
        txtProdi.setText(tblPeserta.getValueAt(row, 4).toString());
        txtNoHp.setText(tblPeserta.getValueAt(row, 5).toString());
        txtEmail.setText(tblPeserta.getValueAt(row, 6).toString());
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if (selectedId <= 0) {
        JOptionPane.showMessageDialog(
        this,
        "Pilih data peserta pada tabel terlebih dahulu."
        );
        return;
        }
        if (!validateForm()) {
        return;
        }
        try {
        Peserta p = getFormData();
        if (dao.update(p)) {
        JOptionPane.showMessageDialog(
        this,
        "Data peserta berhasil diubah."
        );
        refreshData();
        } else {
        JOptionPane.showMessageDialog(
        this,
        "Data tidak ditemukan atau tidak berubah."
        );
        }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
        this,
        "Gagal mengubah data: " + ex.getMessage(),
        "Database Error",
        JOptionPane.ERROR_MESSAGE
        );
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        String keyword = txtCari.getText().trim();
        try {
        if (keyword.isEmpty()) {
        loadTable();
        } else {
        fillTable(dao.search(keyword));
        }
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
        this,
        "Pencarian gagal: " + ex.getMessage(),
        "Database Error",
        JOptionPane.ERROR_MESSAGE
        );
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void refreshData() {
        loadTable();
        clearForm();
        txtCari.setText("");
    }
    
    private void resetForm() {
        txtNama.setText("");
        txtKodeAkses.setText("");
        groupJenis.clearSelection();
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtNama.requestFocus();
        
        txtCari.setText("");
        loadTable();
        clearForm();
    }
    
    private String formatRupiah(double nilai) {
        String angka = String.format(Locale.US, "%,.0f", nilai).replace(',', '.');
        return "Rp" + angka;
    }
    
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranKursus.class.getName())
                    .log(java.util.logging.Level.WARNING, "Nimbus tidak dapat diaktifkan", ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormPendaftaranKursus().setVisible(true);
            }
        });
    }
    
    private void clearForm() {
    
    selectedId = -1;
    txtNim.setText("");
    txtNama.setText("");
    buttonGroup3.clearSelection();
    txtProdi.setText("");
    txtNoHp.setText("");
    txtEmail.setText("");
    txtNim.requestFocus();
    tblPeserta.clearSelection();
    btnUbah.setEnabled(false);
    btnHapus.setEnabled(false);
    }
    
    private String getJenisKelamin() {
    if (rbLaki.isSelected()) {
        return "L";
    }

    if (rbPerempuan.isSelected()) {
        return "P";
    }

    return null;
    }
    
    private Peserta getFormData() {

    Peserta p = new Peserta();

    p.setId(selectedId);
    p.setNim(txtNim.getText().trim());
    p.setNama(txtNama.getText().trim());
    p.setJenisKelamin(getJenisKelamin());
    p.setProdi(txtProdi.getText().trim());
    p.setNoHp(txtNoHp.getText().trim());
    p.setEmail(txtEmail.getText().trim());

    return p;
    }
    
    private boolean validateForm() {

    if (txtNim.getText().trim().isEmpty()
            || txtNama.getText().trim().isEmpty()
            || txtProdi.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "NIM, Nama, dan Prodi wajib diisi."
        );

        return false;
    }

    if (getJenisKelamin() == null) {

        JOptionPane.showMessageDialog(
                this,
                "Pilih jenis kelamin."
        );

        return false;
    }

    return true;
    }
    
    private void configureTable() {

    DefaultTableModel model = new DefaultTableModel(
        new Object[]{
            "ID", "NIM", "Nama", "JK",
            "Prodi", "No. HP", "Email"
        },
        0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    tblPeserta.setModel(model);

    tblPeserta.setSelectionMode(
        javax.swing.ListSelectionModel.SINGLE_SELECTION
    );
    }
    
    private void fillTable(List<Peserta> data) {
    DefaultTableModel model =
    (DefaultTableModel) tblPeserta.getModel();
    model.setRowCount(0);
    for (Peserta p : data) {
    model.addRow(new Object[]{
    p.getId(),
    p.getNim(),
    p.getNama(),
    p.getJenisKelamin(),
    p.getProdi(),
    p.getNoHp(),
    p.getEmail()
    });
    }
    }
    
    private void loadTable() {

    DefaultTableModel model =
            (DefaultTableModel) tblPeserta.getModel();

    model.setRowCount(0);

    try {
        PesertaDAO dao = new PesertaDAO();

        List<Peserta> daftar = dao.findAll();

        for (Peserta p : daftar) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNim(),
                p.getNama(),
                p.getJenisKelamin(),
                p.getProdi(),
                p.getNoHp(),
                p.getEmail()
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Gagal memuat data peserta: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox cbModul;
    private javax.swing.JCheckBox cbRekaman;
    private javax.swing.JCheckBox cbSertifikat;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.ButtonGroup groupJenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rbLaki;
    private javax.swing.JRadioButton rbMahasiswa;
    private javax.swing.JRadioButton rbPerempuan;
    private javax.swing.JRadioButton rbUmum;
    private javax.swing.JTable tblPeserta;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtKodeAkses;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNim;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtProdi;
    private javax.swing.JTextArea txtRingkasan;
    // End of variables declaration//GEN-END:variables
}
