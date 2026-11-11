/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buzinice;

/**
 *
 * @author acer
 */

public class Penjual extends Pengguna {
    private String namaToko;
    private String kategoriToko;

    public Penjual(String nama, String username, String password,
                   String namaToko, String kategoriToko) {

        super(nama, username, password);

        this.namaToko = namaToko;
        this.kategoriToko = kategoriToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getKategoriToko() {
        return kategoriToko;
    }

    public void setKategoriToko(String kategoriToko) {
        this.kategoriToko = kategoriToko;
    }

    public void tampilkanProfilToko() {
        System.out.println("\n===== PROFIL TOKO =====");
        System.out.println("Nama Pemilik : " + getNama());
        System.out.println("Username     : " + getUsername());
        System.out.println("Nama Toko    : " + namaToko);
        System.out.println("Kategori     : " + kategoriToko);
    }
}
