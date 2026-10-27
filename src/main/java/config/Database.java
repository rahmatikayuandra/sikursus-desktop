package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
private static final String URL =
"jdbc:mysql://localhost:3306/db_sikursus";
private static final String USER = "tika";
private static final String PASS = "1234";
private Database() {
// Mencegah object Database dibuat sembarangan.
}
public static Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        throw new SQLException("Driver MySQL tidak ditemukan.", e);
    }

    return DriverManager.getConnection(URL, USER, PASS);
}
}