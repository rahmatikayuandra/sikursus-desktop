package model;

public class Peserta extends Orang {
    private String nim;
    private String jenisKelamin;
    private String prodi;
    private String email;

    // Constructor lengkap (dipakai FormPendaftaranKursus)
    public Peserta(int id, String nama, String noHp, String nim,
                   String jenisKelamin, String prodi, String email) {
        super(id, nama, noHp);
        setNim(nim);
        setJenisKelamin(jenisKelamin);
        setProdi(prodi);
        setEmail(email);
    }

    // Constructor lama, tetap ada supaya kode lain tidak rusak
    public Peserta(int id, String nama, String noHp, String nim, String prodi) {
        this(id, nama, noHp, nim, "", prodi, "");
    }

    public String getNim() {
        return nim;
    }

    public final void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM wajib diisi");
        }
        this.nim = nim.trim();
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public final void setJenisKelamin(String jenisKelamin) {
        if (jenisKelamin == null || jenisKelamin.trim().isEmpty()) {
            this.jenisKelamin = "";
            return;
        }
        String jk = jenisKelamin.trim().toUpperCase();
        if (!jk.equals("L") && !jk.equals("P")) {
            throw new IllegalArgumentException("Jenis kelamin harus L atau P");
        }
        this.jenisKelamin = jk;
    }

    public String getProdi() {
        return prodi;
    }

    public final void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException("Prodi wajib diisi");
        }
        this.prodi = prodi.trim();
    }

    public String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = (email == null) ? "" : email.trim();
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo()
                + " | NIM: " + getNim()
                + " | JK: " + getJenisKelamin()
                + " | Prodi: " + getProdi()
                + " | Email: " + getEmail();
    }
}