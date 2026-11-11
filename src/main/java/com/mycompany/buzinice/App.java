
package com.mycompany.buzinice;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Penjual penjual = null;
        int pilihan;

        System.out.println("================================");
        System.out.println("       SELAMAT DATANG DI");
        System.out.println("          BUZINICE");
        System.out.println("   Business Management System");
        System.out.println("================================");

        do {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Registrasi");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    if (penjual != null) {
                        System.out.println("Akun sudah terdaftar!");
                        break;
                    }

                    System.out.println("\n===== REGISTRASI PENJUAL =====");

                    System.out.print("Nama pemilik : ");
                    String nama = input.nextLine();

                    System.out.print("Username     : ");
                    String username = input.nextLine();

                    System.out.print("Password     : ");
                    String password = input.nextLine();

                    System.out.print("Nama toko    : ");
                    String toko = input.nextLine();

                    System.out.print("Kategori toko: ");
                    String kategori = input.nextLine();

                    penjual = new Penjual(
                        nama, username, password, toko, kategori
                    );

                    System.out.println("\nRegistrasi berhasil!");
                    break;

                case 2:
                    if (penjual == null) {
                        System.out.println(
                            "Silakan registrasi terlebih dahulu!"
                        );
                        break;
                    }

                    System.out.println("\n===== LOGIN PENJUAL =====");

                    System.out.print("Username : ");
                    String userLogin = input.nextLine();

                    System.out.print("Password : ");
                    String passLogin = input.nextLine();

                    if (penjual.login(userLogin, passLogin)) {

                        System.out.println("\nLogin berhasil!");
                        System.out.println(
                            "Selamat datang, " + penjual.getNama()
                        );

                        int menuPenjual = 0;

                        while (menuPenjual != 2) {

                            System.out.println(
                                "\n===== DASHBOARD BUZINICE ====="
                            );
                            System.out.println("1. Profil Toko");
                            System.out.println("2. Logout");
                            System.out.print("Pilih menu: ");

                            menuPenjual = input.nextInt();
                            input.nextLine();

                            switch (menuPenjual) {

                                case 1:
                                    penjual.tampilkanProfilToko();
                                    break;

                                case 2:
                                    System.out.println("Berhasil logout.");
                                    break;

                                default:
                                    System.out.println(
                                        "Pilihan tidak tersedia!"
                                    );
                            }
                        }

                    } else {
                        System.out.println(
                            "Username atau password salah!"
                        );
                    }
                    break;

                case 3:
                    System.out.println(
                        "\nTerima kasih telah menggunakan Buzinice!"
                    );
                    break;

                default:
                    System.out.println(
                        "Pilihan tidak tersedia! Silakan coba lagi."
                    );
            }

        } while (pilihan != 3);

        input.close();
    }
}
