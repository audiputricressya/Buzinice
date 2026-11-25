package com.mycompany.buzinice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ProdukCsv {

    private static final Path FILE = Paths.get("produk.csv");
    private static final String HEADER = "nama,harga,stok";

    public static List<Produk> muat() {
        List<Produk> hasil = new ArrayList<>();

        if (!Files.exists(FILE)) {
            return hasil;
        }

        try {
            List<String> baris = Files.readAllLines(FILE, StandardCharsets.UTF_8);

            for (int i = 1; i < baris.size(); i++) {
                String line = baris.get(i).trim();

                if (line.isEmpty()) {
                    continue;
                }

                try {
                    List<String> kolom = pecahBaris(line);

                    if (kolom.size() != 3) {
                        continue;
                    }

                    String nama = kolom.get(0);
                    long harga = Long.parseLong(kolom.get(1).trim());
                    int stok = Integer.parseInt(kolom.get(2).trim());

                    hasil.add(new Produk(nama, harga, stok));
                } catch (RuntimeException ex) {
                    // Baris yang formatnya tidak sesuai akan dilewati.
                }
            }
        } catch (IOException ex) {
            System.err.println("Gagal membaca produk.csv: " + ex.getMessage());
        }

        return hasil;
    }

    public static void simpan(List<Produk> daftar) throws IOException {
        List<String> baris = new ArrayList<>();
        baris.add(HEADER);

        for (Produk produk : daftar) {
            baris.add(escape(produk.getNama()) + ","
                    + produk.getHarga() + ","
                    + produk.getStok());
        }

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
        StringBuilder isiKolom = new StringBuilder();
        boolean dalamKutip = false;

        for (int i = 0; i < line.length(); i++) {
            char karakter = line.charAt(i);

            if (dalamKutip) {
                if (karakter == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        isiKolom.append('"');
                        i++;
                    } else {
                        dalamKutip = false;
                    }
                } else {
                    isiKolom.append(karakter);
                }
            } else if (karakter == '"') {
                dalamKutip = true;
            } else if (karakter == ',') {
                kolom.add(isiKolom.toString());
                isiKolom.setLength(0);
            } else {
                isiKolom.append(karakter);
            }
        }

        kolom.add(isiKolom.toString());
        return kolom;
    }
}
