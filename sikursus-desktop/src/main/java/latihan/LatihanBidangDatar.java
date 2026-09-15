package latihan;

import java.util.Scanner;

public class LatihanBidangDatar {
    public static void main(String[] args) {

        // Membuat Scanner
        Scanner input = new Scanner(System.in);

        // Variabel
        double panjang;
        double lebar;
        double luas;
        double keliling;

        // Input
        System.out.print("Masukkan panjang : ");
        panjang = input.nextDouble();

        System.out.print("Masukkan lebar   : ");
        lebar = input.nextDouble();

        // Menghitung luas
        luas = panjang * lebar;

        // Menghitung keliling
        keliling = 2 * (panjang + lebar);

        // Output
        System.out.println();
        System.out.println("=== HASIL PERHITUNGAN ===");
        System.out.printf("Luas      : %.2f%n", luas);
        System.out.printf("Keliling  : %.2f%n", keliling);

        // Menutup Scanner
        input.close();
    }
}
