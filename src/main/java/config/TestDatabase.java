
package config;

import java.sql.Connection;

public class TestDatabase {

    public static void main(String[] args) {

        try {
            Connection conn = Database.getConnection();

            System.out.println("Koneksi database berhasil!");
            System.out.println("Database: " +
                conn.getCatalog());

            conn.close();

        } catch (Exception e) {
            System.out.println("Koneksi database gagal!");
            e.printStackTrace();
        }
    }
}
