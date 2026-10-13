package view;

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

public class FormPendaftaranKursus extends javax.swing.JFrame {

    private final Color warnaLatar = new Color(248, 250, 252);
    private final Color warnaHeader = new Color(0, 102, 204);
    private final Color warnaProses = new Color(0, 102, 153);
    private final Color warnaReset = new Color(255, 51, 51);

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

    groupJenis.add(rbMahasiswa);
    groupJenis.add(rbUmum);

    txtRingkasan.setEditable(false);
    txtRingkasan.setLineWrap(false);

    getRootPane().setDefaultButton(btnProses);

    setMinimumSize(new Dimension(760, 650));
    setSize(new Dimension(920, 720));
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
        btnProses = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jLabel1.setText("FORM PENDAFTARAN KURSUS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 49, 440, -1));
        getContentPane().add(txtKodeAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 80, 440, -1));

        groupJenis.add(rbMahasiswa);
        rbMahasiswa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbMahasiswa.setText("Mahasiswa");
        getContentPane().add(rbMahasiswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 107, -1, -1));

        groupJenis.add(rbUmum);
        rbUmum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbUmum.setText("Umum");
        getContentPane().add(rbUmum, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 108, -1, -1));

        cmbKursus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Data Science", "UI/UX" }));
        getContentPane().add(cmbKursus, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 139, -1, -1));

        cbModul.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbModul.setText("Modul Cetak");
        getContentPane().add(cbModul, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 168, -1, -1));

        cbSertifikat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbSertifikat.setText("Sertifikat");
        getContentPane().add(cbSertifikat, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 168, -1, -1));

        cbRekaman.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbRekaman.setText("Akses Rekaman");
        getContentPane().add(cbRekaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 168, -1, -1));

        txtRingkasan.setColumns(20);
        txtRingkasan.setRows(5);
        jScrollPane1.setViewportView(txtRingkasan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 200, 440, 260));

        btnProses.setBackground(new java.awt.Color(0, 204, 51));
        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProses.setText("Proses");
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });
        getContentPane().add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 478, -1, -1));

        btnReset.setBackground(new java.awt.Color(255, 0, 0));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 478, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Kode Akses");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 81, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Nama Peserta");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Kursus");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 142, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Jenis Peserta");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 111, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fasilitas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 172, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ringkasan");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 200, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
         if (!validasiInput()) {
            return;
        }

        String nama = txtNama.getText().trim();
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
    }//GEN-LAST:event_btnProsesActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox cbModul;
    private javax.swing.JCheckBox cbRekaman;
    private javax.swing.JCheckBox cbSertifikat;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.ButtonGroup groupJenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbMahasiswa;
    private javax.swing.JRadioButton rbUmum;
    private javax.swing.JPasswordField txtKodeAkses;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextArea txtRingkasan;
    // End of variables declaration//GEN-END:variables
}
