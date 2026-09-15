package view;

public class FormPendaftaranAwal extends javax.swing.JFrame {
    public FormPendaftaranAwal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtBiaya = new javax.swing.JTextField();
        cmbKursus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHasil = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jLabel1.setText("FORM PENDAFTARAN");

        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtJumlah.setText("Jumlah");
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNama.setText("Nama");
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtBiaya.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtBiaya.setText("Biaya");
        txtBiaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBiayaActionPerformed(evt);
            }
        });

        cmbKursus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Data Science", "UI/UX" }));
        cmbKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKursusActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Pilih Kursus");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Nama Peserta");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Biaya Kursus");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Jumlah");

        btnReset.setBackground(new java.awt.Color(201, 125, 125));
        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnProcess.setBackground(new java.awt.Color(125, 201, 139));
        btnProcess.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnProcess.setText("Process");
        btnProcess.setAutoscrolls(true);
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        txtHasil.setColumns(20);
        txtHasil.setRows(5);
        jScrollPane1.setViewportView(txtHasil);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel3)
                .addGap(53, 53, 53)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel4)
                .addGap(61, 61, 61)
                .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel5)
                .addGap(91, 91, 91)
                .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addGap(67, 67, 67)
                .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(btnProcess)
                .addGap(17, 17, 17)
                .addComponent(btnReset))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProcess)
                    .addComponent(btnReset))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtBiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBiayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBiayaActionPerformed

    private void cmbKursusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKursusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKursusActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        // Mengambil nama
        String nama = txtNama.getText().trim();

        // Validasi nama
        if (nama.isEmpty() || nama.equalsIgnoreCase("Nama")) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Nama peserta harus diisi!",
                    "Peringatan",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            txtNama.requestFocus();
            return;
        }

        int biaya;
        int jumlah;

        // =========================
        // VALIDASI BIAYA
        // =========================
        try {
            biaya = Integer.parseInt(txtBiaya.getText().trim());
        } catch (NumberFormatException e) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Biaya Kursus harus berupa angka integer!\n"
                    + "Contoh: 150000",
                    "Biaya Tidak Valid",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            txtBiaya.requestFocus();
            return;
        }

        // Validasi biaya tidak boleh negatif
        if (biaya < 0) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Biaya Kursus tidak boleh kurang dari 0!",
                    "Biaya Tidak Valid",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            txtBiaya.requestFocus();
            return;
        }

        // =========================
        // VALIDASI JUMLAH
        // =========================
        try {
            jumlah = Integer.parseInt(txtJumlah.getText().trim());
        } catch (NumberFormatException e) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Jumlah harus berupa angka integer!\n"
                    + "Contoh: 2",
                    "Jumlah Tidak Valid",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            txtJumlah.requestFocus();
            return;
        }

        // Validasi jumlah
        if (jumlah <= 0) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Jumlah harus lebih dari 0!",
                    "Jumlah Tidak Valid",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            txtJumlah.requestFocus();
            return;
        }

        // =========================
        // MENGAMBIL DATA KURSUS
        // =========================
        String kursus = cmbKursus.getSelectedItem().toString();

        // =========================
        // MENGHITUNG TOTAL
        // =========================
        int totalHarga = biaya * jumlah;

        // =========================
        // MENAMPILKAN HASIL
        // =========================
        txtHasil.setText(
                "INFORMASI PENDAFTARAN\n"
                + "==============================\n"
                + "Nama Peserta : " + nama + "\n"
                + "Biaya Kursus : Rp " + biaya + "\n"
                + "Jumlah       : " + jumlah + "\n"
                + "Pilih Kursus : " + kursus + "\n"
                + "Total Harga  : Rp " + totalHarga
        );

    }//GEN-LAST:event_btnProcessActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtNama.setText("");
        txtBiaya.setText("");
        txtJumlah.setText("");

        // Mengembalikan pilihan kursus ke pilihan pertama
        cmbKursus.setSelectedIndex(0);

        // Menghapus hasil
        txtHasil.setText("");

        // Cursor kembali ke Nama
        txtNama.requestFocus();
    }//GEN-LAST:event_btnResetActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPendaftaranAwal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextArea txtHasil;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
