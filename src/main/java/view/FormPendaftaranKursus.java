package view;

import dao.PesertaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
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
import model.Peserta;

public class FormPendaftaranKursus extends JFrame {

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
    private JLabel lblNim;          // Label Baru
    private JLabel lblProdi;        // Label Baru
    private JLabel lblNoHp;         // Label Baru
    private JLabel lblEmail;        // Label Baru
    private JLabel lblKodeAkses;
    private JLabel lblJenis;
    private JLabel lblKursus;
    private JLabel lblFasilitas;
    private JLabel lblRingkasan;

    private JTextField txtNama;
    private JTextField txtNim;      // Field Baru
    private JTextField txtProdi;    // Field Baru
    private JTextField txtNoHp;     // Field Baru
    private JTextField txtEmail;    // Field Baru
    private JPasswordField txtKodeAkses;
    private JRadioButton rbMahasiswa; // Digunakan sebagai Laki-laki ("L") sesuai modul
    private JRadioButton rbUmum;      // Digunakan sebagai Perempuan ("P") sesuai modul
    private ButtonGroup groupJenis;   // buttonGroupJenisKelamin
    private JComboBox<String> cmbKursus;
    private JCheckBox cbModul;
    private JCheckBox cbSertifikat;
    private JCheckBox cbRekaman;
    private JTextArea txtRingkasan;
    private JScrollPane scrollRingkasan;
    private JButton btnProses;
    private JButton btnReset;

    public FormPendaftaranKursus() {
        initComponents();

        groupJenis.add(rbMahasiswa);
        groupJenis.add(rbUmum);

        txtRingkasan.setEditable(false);
        txtRingkasan.setLineWrap(false);
        getRootPane().setDefaultButton(btnProses);

        setMinimumSize(new Dimension(760, 750));
        setSize(new Dimension(920, 820));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelUtama = new JPanel(new BorderLayout());
        panelHeader = new JPanel(new BorderLayout());
        panelForm = new JPanel(new GridBagLayout());
        panelJenis = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        panelFasilitas = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 0));

        lblJudul = new JLabel("Form Pendaftaran Kursus", SwingConstants.CENTER);
        lblNim = new JLabel("NIM");
        lblNama = new JLabel("Nama Peserta");
        lblJenis = new JLabel("Jenis Kelamin");
        lblProdi = new JLabel("Program Studi");
        lblNoHp = new JLabel("Nomor HP");
        lblEmail = new JLabel("Email");
        lblKodeAkses = new JLabel("Kode Akses");
        lblKursus = new JLabel("Kursus");
        lblFasilitas = new JLabel("Fasilitas");
        lblRingkasan = new JLabel("Ringkasan");

        txtNim = new JTextField();
        txtNama = new JTextField();
        txtProdi = new JTextField();
        txtNoHp = new JTextField();
        txtEmail = new JTextField();
        txtKodeAkses = new JPasswordField();

        rbMahasiswa = new JRadioButton("Laki-laki");
        rbUmum = new JRadioButton("Perempuan");
        groupJenis = new ButtonGroup();

        cmbKursus = new JComboBox<String>(new String[]{
            "-- Pilih Kursus --",
            "Java Desktop",
            "Data Science",
            "Web Programming",
            "UI/UX Design"
        });

        cbModul = new JCheckBox("Modul Cetak");
        cbSertifikat = new JCheckBox("Sertifikat");
        cbRekaman = new JCheckBox("Akses Rekaman");

        txtRingkasan = new JTextArea(11, 20);
        scrollRingkasan = new JScrollPane(txtRingkasan);

        btnProses = new JButton("Simpan"); // Diubah menjadi Simpan sesuai modul
        btnReset = new JButton("Reset");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Pendaftaran Kursus");

        panelUtama.setBackground(warnaLatar);
        panelHeader.setBackground(warnaHeader);
        panelForm.setBackground(warnaLatar);
        panelJenis.setBackground(warnaLatar);
        panelFasilitas.setBackground(warnaLatar);
        panelTombol.setBackground(warnaLatar);

        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setPreferredSize(new Dimension(100, 70));
        panelHeader.add(lblJudul, BorderLayout.CENTER);

        rbMahasiswa.setBackground(warnaLatar);
        rbUmum.setBackground(warnaLatar);
        cbModul.setBackground(warnaLatar);
        cbSertifikat.setBackground(warnaLatar);
        cbRekaman.setBackground(warnaLatar);

        panelJenis.add(rbMahasiswa);
        panelJenis.add(rbUmum);

        panelFasilitas.add(cbModul);
        panelFasilitas.add(cbSertifikat);
        panelFasilitas.add(cbRekaman);

        btnProses.setBackground(warnaProses);
        btnProses.setForeground(Color.WHITE);
        btnProses.setFocusPainted(false);
        btnProses.setPreferredSize(new Dimension(130, 38));
        btnProses.addActionListener(evt -> btnProsesActionPerformed());

        btnReset.setBackground(warnaReset);
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(130, 38));
        btnReset.addActionListener(evt -> btnResetActionPerformed());

        panelTombol.add(btnProses);
        panelTombol.add(btnReset);

        txtNim.setPreferredSize(new Dimension(420, 36));
        txtNama.setPreferredSize(new Dimension(420, 36));
        txtProdi.setPreferredSize(new Dimension(420, 36));
        txtNoHp.setPreferredSize(new Dimension(420, 36));
        txtEmail.setPreferredSize(new Dimension(420, 36));
        txtKodeAkses.setPreferredSize(new Dimension(420, 36));
        cmbKursus.setPreferredSize(new Dimension(420, 36));
        txtKodeAkses.setToolTipText("Kode akses demo praktikum: PBO2026");

        txtRingkasan.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        txtRingkasan.setBackground(Color.WHITE);
        scrollRingkasan.setPreferredSize(new Dimension(520, 150));

        panelForm.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        // Menyusun baris Form menggunakan GridBagLayout
        addRow(0, lblNim, txtNim, 0.0);
        addRow(1, lblNama, txtNama, 0.0);
        addRow(2, lblJenis, panelJenis, 0.0);
        addRow(3, lblProdi, txtProdi, 0.0);
        addRow(4, lblNoHp, txtNoHp, 0.0);
        addRow(5, lblEmail, txtEmail, 0.0);
        addRow(6, lblKodeAkses, txtKodeAkses, 0.0);
        addRow(7, lblKursus, cmbKursus, 0.0);
        addRow(8, lblFasilitas, panelFasilitas, 0.0);
        addRow(9, lblRingkasan, scrollRingkasan, 1.0);

        GridBagConstraints tombol = new GridBagConstraints();
        tombol.gridx = 1;
        tombol.gridy = 10;
        tombol.weightx = 1.0;
        tombol.fill = GridBagConstraints.HORIZONTAL;
        tombol.anchor = GridBagConstraints.CENTER;
        tombol.insets = new Insets(20, 8, 0, 0);
        panelForm.add(panelTombol, tombol);

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(panelForm, BorderLayout.CENTER);
        setContentPane(panelUtama);
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

    private String getJenisKelamin() {
        if (rbMahasiswa.isSelected()) {
            return "L";
        }
        if (rbUmum.isSelected()) {
            return "P";
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

    private void clearForm() {
        txtNim.setText("");
        txtNama.setText("");
        groupJenis.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtKodeAkses.setText("");
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtNim.requestFocus();
    }

    private void btnProsesActionPerformed() {
// 1. Ambil Input & Validasi Dasar
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();
        String kodeAkses = new String(txtKodeAkses.getPassword());
        String jenisKelamin = getJenisKelamin();
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "NIM, nama, jenis kelamin, dan prodi wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!kodeAkses.equals("PBO2026")) {
            JOptionPane.showMessageDialog(this,
                    "Kode akses demo salah.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            txtKodeAkses.requestFocus();
            return;
        }
        if (cmbKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Pilih kursus terlebih dahulu.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            cmbKursus.requestFocus();
            return;
        }
// 2. Tampilkan Ringkasan di TextArea Terlebih Dahulu
        String kursus = cmbKursus.getSelectedItem().toString();
        double biayaDasar = hitungBiayaDasar(kursus);
        double diskon = jenisKelamin.equals("L") ? biayaDasar * 0.05 : biayaDasar * 0.10; // Contoh logika variasi diskon gender
        double biayaFasilitas = hitungBiayaFasilitas();
        double total = biayaDasar - diskon + biayaFasilitas;
        String ringkasan
                = "PENDAFTARAN & PENYIMPANAN DATA\n"
                + "================================\n"
                + "NIM             : " + nim + "\n"
                + "Nama            : " + nama + "\n"
                + "Gender          : " + (jenisKelamin.equals("L") ? "Laki-laki" : "Perempuan") + "\n"
                + "Prodi           : " + prodi + "\n"
                + "No HP           : " + noHp + "\n"
                + "Email           : " + email + "\n"
                + "Kursus          : " + kursus + "\n"
                + "Biaya Dasar     : " + formatRupiah(biayaDasar) + "\n"
                + "Fasilitas:\n" + buatRingkasanFasilitas()
                + "================================\n"
                + "TOTAL BIAYA     : " + formatRupiah(total);
        txtRingkasan.setText(ringkasan);
        txtRingkasan.setCaretPosition(0);
// 3. Bentuk Object Model Peserta (Menyesuaikan inheritance Orang (id=0 sebagai auto-increment))
        Peserta peserta = new Peserta(0, nama, jenisKelamin, prodi, noHp, email, nim);
// 4. Panggil DAO untuk Menyimpan Data Ke Database
        PesertaDAO dao = new PesertaDAO();
        try {
            boolean berhasil = dao.insert(peserta);
            if (berhasil) {
                JOptionPane.showMessageDialog(this,
                        "Data peserta berhasil disimpan ke database.",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data tidak tersimpan.",
                        "Informasi",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnResetActionPerformed() {
        int jawab = JOptionPane.showConfirmDialog(
                this,
                "Kosongkan seluruh input form?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            clearForm();
        }
    }

    private String formatRupiah(double nilai) {
        String angka = String.format(Locale.US, "%,.0f", nilai).replace(',', '.');
        return "Rp" + angka;
    }

    public static void main(String[] args) {
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
        java.awt.EventQueue.invokeLater(() -> new FormPendaftaranKursus().setVisible(true));
    }
}