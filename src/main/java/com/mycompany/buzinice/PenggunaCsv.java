package com.mycompany.buzinice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PenggunaCsv {

    private static final Path FILE = Paths.get(System.getProperty("user.dir"), "pengguna.csv");
    private static final String HEADER = "nama,username,password,namaToko,kategoriToko";

    public static Penjual muat() {
        try {
            if (!Files.exists(FILE)) {
                Files.write(FILE, List.of(HEADER), StandardCharsets.UTF_8);
                return null;
            }
            List<String> baris = Files.readAllLines(FILE, StandardCharsets.UTF_8);
            for (int i = 1; i < baris.size(); i++) {
                if (baris.get(i).trim().isEmpty()) continue;
                List<String> kolom = pecahBaris(baris.get(i));
                if (kolom.size() == 5) {
                    return new Penjual(kolom.get(0), kolom.get(1), kolom.get(2), kolom.get(3), kolom.get(4));
                }
            }
        } catch (IOException | RuntimeException ex) {
            System.err.println("Gagal membaca pengguna.csv: " + ex.getMessage());
        }
        return null;
    }

    public static void simpan(Penjual penjual) throws IOException {
        List<String> baris = new ArrayList<>();
        baris.add(HEADER);
        baris.add(escape(penjual.getNama()) + "," + escape(penjual.getUsername()) + ","
                + escape(penjual.getPassword()) + "," + escape(penjual.getNamaToko()) + ","
                + escape(penjual.getKategoriToko()));
        Files.write(FILE, baris, StandardCharsets.UTF_8);
    }

    private static String escape(String teks) {
        if (teks.contains(",") || teks.contains("\"")) {
            return "\"" + teks.replace("\"", "\"\"") + "\"";
        }
        return teks;
    }

    private static List<String> pecahBaris(String line) {
        List<String> kolom = new ArrayList<>();
        StringBuilder isi = new StringBuilder();
        boolean kutip = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (kutip) {
                if (c == '\"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '\"') {
                        isi.append('\"'); i++;
                    } else kutip = false;
                } else isi.append(c);
            } else if (c == '\"') kutip = true;
            else if (c == ',') { kolom.add(isi.toString()); isi.setLength(0); }
            else isi.append(c);
        }
        kolom.add(isi.toString());
        return kolom;
    }
}
