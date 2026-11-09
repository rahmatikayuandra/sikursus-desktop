
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Konfigurasi Database MySQL MAMP
    private static final String URL =
        "jdbc:mysql://localhost:8889/db_sikursus?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true";

    private static final String USER = "tika";
    private static final String PASS = "1234";

    private Database() {
        // Mencegah object Database dibuat sembarangan.
    }

    public static Connection getConnection() throws SQLException {

        try {
            // Memanggil driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "Driver MySQL tidak ditemukan. Periksa dependency Maven.",
                e
            );
        }

        // Membuat koneksi database
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
