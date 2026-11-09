
package model;

public class Instruktur extends Orang {

    private String keahlian;
    private String email;

    // Constructor kosong untuk kebutuhan form
    public Instruktur() {
        super();
        this.keahlian = "";
        this.email = "";
    }

    // Constructor lama tetap dipertahankan
    public Instruktur(int id, String nama, String noHp,
                      String keahlian) {

        super(id, nama, noHp);
        setKeahlian(keahlian);
        setEmail("");
    }

    // Constructor lengkap untuk mengambil data dari database
    public Instruktur(int id, String nama, String noHp,
                      String keahlian, String email) {

        super(id, nama, noHp);
        setKeahlian(keahlian);
        setEmail(email);
    }

    // GETTER DAN SETTER KEAHLIAN
    public String getKeahlian() {
        return keahlian;
    }

    public final void setKeahlian(String keahlian) {

        if (keahlian == null || keahlian.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Keahlian wajib diisi"
            );
        }

        this.keahlian = keahlian.trim();
    }

    // GETTER DAN SETTER EMAIL
    public String getEmail() {
        return email;
    }

    public final void setEmail(String email) {

        String nilai = email == null ? "" : email.trim();

        if (!nilai.isEmpty()
                && !nilai.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new IllegalArgumentException(
                "Format email tidak valid"
            );
        }

        this.email = nilai;
    }

    // OVERRIDE METHOD DARI CLASS ORANG
    @Override
    public String getInfo() {

        return "INSTRUKTUR | "
                + super.getInfo()
                + " | Keahlian: " + getKeahlian()
                + " | Email: "
                + (getEmail().isEmpty() ? "-" : getEmail());
    }
}
