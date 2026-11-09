
package view;

import dao.UserDAO;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.User;
import util.UserSession;

public class LoginFrame extends JFrame {

    private final JTextField txtUsername = new JTextField();
    private final JPasswordField pwdPassword = new JPasswordField();

    private final UserDAO dao = new UserDAO();

    private JButton btnLogin;
    private JButton btnKeluar;
    private JButton btnTogglePassword;

    private boolean passwordVisible = false;

    private final Color NAVY = new Color(15, 35, 70);
    private final Color BLUE = new Color(37, 99, 235);
    private final Color LIGHT_BG = new Color(248, 250, 252);
    private final Color TEXT = new Color(30, 41, 59);
    private final Color MUTED = new Color(100, 116, 139);
    private final Color BORDER = new Color(226, 232, 240);

    public LoginFrame() {
        super("Login - SIKURSUS Desktop");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        build();

        setSize(900, 560);
        setLocationRelativeTo(null);
    }

    private void build() {

        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(Color.WHITE);

        root.add(createBrandPanel());
        root.add(createLoginPanel());

        setContentPane(root);
        getRootPane().setDefaultButton(null);
    }

    // =========================================
    // PANEL KIRI: BRANDING
    // =========================================

    private JPanel createBrandPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(NAVY);
        panel.setBorder(new EmptyBorder(55, 45, 45, 45));

        JLabel logo = new JLabel("SIKURSUS");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("COURSE MANAGEMENT SYSTEM");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subtitle.setForeground(new Color(147, 197, 253));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(logo);
        panel.add(Box.createVerticalStrut(5));
        panel.add(subtitle);

        panel.add(Box.createVerticalStrut(100));

        JLabel heading = new JLabel(
                "<html>Kelola Kursus<br>Lebih Mudah<br>dan Terorganisir.</html>"
        );

        heading.setFont(new Font("Segoe UI", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
                "<html><div style='width:290px;'>"
                + "Sistem informasi manajemen kursus untuk "
                + "mengelola peserta, instruktur, kelas, "
                + "pendaftaran, dan pembayaran dalam satu aplikasi."
                + "</div></html>"
        );

        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setForeground(new Color(203, 213, 225));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(heading);
        panel.add(Box.createVerticalStrut(20));
        panel.add(description);

        panel.add(Box.createVerticalGlue());

        JLabel footer = new JLabel("SIKURSUS Desktop Application");
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footer.setForeground(new Color(148, 163, 184));
        footer.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(footer);

        return panel;
    }

    // =========================================
    // PANEL KANAN: FORM LOGIN
    // =========================================

    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(LIGHT_BG);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(38, 42, 38, 42));
        form.setPreferredSize(new Dimension(390, 430));

        JLabel welcome = new JLabel("Selamat Datang!");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 27));
        welcome.setForeground(TEXT);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel instruction = new JLabel("Silakan login untuk melanjutkan.");
        instruction.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        instruction.setForeground(MUTED);
        instruction.setAlignmentX(Component.LEFT_ALIGNMENT);

        form.add(welcome);
        form.add(Box.createVerticalStrut(8));
        form.add(instruction);
        form.add(Box.createVerticalStrut(35));

        // Username
        JLabel lblUsername = createLabel("Username");
        form.add(lblUsername);
        form.add(Box.createVerticalStrut(8));

        styleTextField(txtUsername);
        txtUsername.setToolTipText("Masukkan username akun kamu");
        form.add(txtUsername);

        form.add(Box.createVerticalStrut(22));

        // Password
        JLabel lblPassword = createLabel("Password");
        form.add(lblPassword);
        form.add(Box.createVerticalStrut(8));

        styleTextField(pwdPassword);
        pwdPassword.setToolTipText("Masukkan password akun kamu");

        JPanel passwordPanel = new JPanel(new BorderLayout(5, 0));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        passwordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnTogglePassword = new JButton("Lihat");
        btnTogglePassword.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnTogglePassword.setForeground(BLUE);
        btnTogglePassword.setBackground(Color.WHITE);
        btnTogglePassword.setFocusPainted(false);
        btnTogglePassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnTogglePassword.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        passwordPanel.add(pwdPassword, BorderLayout.CENTER);
        passwordPanel.add(btnTogglePassword, BorderLayout.EAST);

        form.add(passwordPanel);

        form.add(Box.createVerticalStrut(30));

        // Tombol login
        btnLogin = new JButton("Login");
        styleButton(btnLogin, BLUE);

        btnLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));

        form.add(btnLogin);

        form.add(Box.createVerticalStrut(12));

        // Tombol keluar
        btnKeluar = new JButton("Keluar");
        styleButton(btnKeluar, new Color(241, 245, 249));
        btnKeluar.setForeground(TEXT);

        btnKeluar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnKeluar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));

        form.add(btnKeluar);

        form.add(Box.createVerticalStrut(22));

        JLabel demo = new JLabel(
                "<html><center>"
                + "Demo Account<br>"
                + "admin_demo / Demo#2026<br>"
                + "operator_demo / Demo#2026"
                + "</center></html>"
        );

        demo.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        demo.setForeground(MUTED);
        demo.setAlignmentX(Component.CENTER_ALIGNMENT);

        form.add(demo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(form, gbc);

        // Event
        btnLogin.addActionListener(e -> login());

        btnKeluar.addActionListener(e -> {
            System.exit(0);
        });

        btnTogglePassword.addActionListener(e -> togglePassword());

        txtUsername.addActionListener(e -> pwdPassword.requestFocusInWindow());
        pwdPassword.addActionListener(e -> login());

        return panel;
    }

    // =========================================
    // KOMPONEN BANTUAN
    // =========================================

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(TEXT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        return label;
    }

    private void styleTextField(JTextField field) {

        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        field.setPreferredSize(new Dimension(300, 43));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleButton(JButton button, Color background) {

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBackground(background);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
    }

    // =========================================
    // TAMPILKAN / SEMBUNYIKAN PASSWORD
    // =========================================

    private void togglePassword() {

        passwordVisible = !passwordVisible;

        if (passwordVisible) {
            pwdPassword.setEchoChar((char) 0);
            btnTogglePassword.setText("Sembunyi");
        } else {
            pwdPassword.setEchoChar(
                    (Character) UIManager.get("PasswordField.echoChar")
            );
            btnTogglePassword.setText("Lihat");
        }

        pwdPassword.requestFocusInWindow();
    }

    // =========================================
    // PROSES LOGIN
    // =========================================

    private void login() {

        String username = txtUsername.getText().trim();
        char[] password = pwdPassword.getPassword();

        try {

            if (username.isEmpty() || password.length == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Username dan password wajib diisi.",
                        "Validasi Login",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            btnLogin.setEnabled(false);
            btnLogin.setText("Memproses...");

            User user = dao.authenticate(username, password);

            if (user == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login gagal. Periksa username dan password kamu.",
                        "Login Gagal",
                        JOptionPane.WARNING_MESSAGE
                );

                pwdPassword.setText("");
                pwdPassword.requestFocusInWindow();

                return;
            }

            UserSession.setCurrentUser(user);

            dispose();

            SwingUtilities.invokeLater(() ->
                    new DashboardFrame().setVisible(true)
            );

        } catch (SQLException ex) {

            ViewUtil.sqlError(this, "Login", ex);

        } finally {

            Arrays.fill(password, '\0');

            btnLogin.setEnabled(true);
            btnLogin.setText("Login");
        }
    }
}
