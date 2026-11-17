
package com.mycompany.buzinice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    // Data akun penjual (sama seperti versi konsol: hanya 1 akun)
    private Penjual penjual = null;

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Buzinice - Business Management System");
        tampilkanMenuUtama();
        stage.show();
    }

    // =====================================================
    // TAHAP 1: DESAIN ANTARMUKA (GUI)
    // =====================================================

    // ===== MENU UTAMA =====
    private void tampilkanMenuUtama() {
        Label lblSelamat = new Label("SELAMAT DATANG DI");
        Label lblJudul = new Label("BUZINICE");
        lblJudul.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        Label lblSub = new Label("Business Management System");

        Label lblMenu = new Label("===== MENU UTAMA =====");

        Button btnRegistrasi = new Button("1. Registrasi");
        Button btnLogin = new Button("2. Login");
        Button btnKeluar = new Button("3. Keluar");
        btnRegistrasi.setPrefWidth(200);
        btnLogin.setPrefWidth(200);
        btnKeluar.setPrefWidth(200);

        // ----- TAHAP 2: Event Handling -----
        btnRegistrasi.setOnAction(e -> {
            if (penjual != null) {
                // TAHAP 3: peringatan jika akun sudah ada
                tampilkanAlert(AlertType.WARNING, "Registrasi",
                        "Akun sudah terdaftar!");
                return;
            }
            tampilkanFormRegistrasi();
        });

        btnLogin.setOnAction(e -> {
            if (penjual == null) {
                // TAHAP 3: peringatan jika belum registrasi
                tampilkanAlert(AlertType.WARNING, "Login",
                        "Silakan registrasi terlebih dahulu!");
                return;
            }
            tampilkanFormLogin();
        });

        btnKeluar.setOnAction(e -> {
            tampilkanAlert(AlertType.INFORMATION, "Keluar",
                    "Terima kasih telah menggunakan Buzinice!");
            stage.close();
        });

        VBox root = new VBox(12, lblSelamat, lblJudul, lblSub, lblMenu,
                btnRegistrasi, btnLogin, btnKeluar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 450, 450));
    }

    // ===== FORM REGISTRASI PENJUAL =====
    private void tampilkanFormRegistrasi() {
        Label lblJudul = new Label("===== REGISTRASI PENJUAL =====");
        lblJudul.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblNama = new Label("Nama pemilik :");
        Label lblUsername = new Label("Username :");
        Label lblPassword = new Label("Password :");
        Label lblToko = new Label("Nama toko :");
        Label lblKategori = new Label("Kategori toko :");

        TextField txtNama = new TextField();
        TextField txtUsername = new TextField();
        PasswordField txtPassword = new PasswordField();
        TextField txtToko = new TextField();
        TextField txtKategori = new TextField();

        Button btnDaftar = new Button("Daftar");
        Button btnKembali = new Button("Kembali");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(lblNama, 0, 0);
        grid.add(txtNama, 1, 0);
        grid.add(lblUsername, 0, 1);
        grid.add(txtUsername, 1, 1);
        grid.add(lblPassword, 0, 2);
        grid.add(txtPassword, 1, 2);
        grid.add(lblToko, 0, 3);
        grid.add(txtToko, 1, 3);
        grid.add(lblKategori, 0, 4);
        grid.add(txtKategori, 1, 4);

        HBox boxTombol = new HBox(10, btnDaftar, btnKembali);
        boxTombol.setAlignment(Pos.CENTER);

        // ----- TAHAP 2: Event Handling -----
        btnDaftar.setOnAction(e -> {
            // Ambil teks dari inputan
            String nama = txtNama.getText().trim();
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String toko = txtToko.getText().trim();
            String kategori = txtKategori.getText().trim();

            // ----- TAHAP 3: Validasi kolom kosong -----
            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()
                    || toko.isEmpty() || kategori.isEmpty()) {
                tampilkanAlert(AlertType.WARNING, "Validasi",
                        "Semua kolom wajib diisi!");
                return;
            }

            // Proses data dengan try-catch agar program tidak crash
            try {
                penjual = new Penjual(
                        nama, username, password, toko, kategori
                );
                tampilkanAlert(AlertType.INFORMATION, "Registrasi",
                        "Registrasi berhasil!");
                tampilkanMenuUtama();
            } catch (Exception ex) {
                tampilkanAlert(AlertType.ERROR, "Error",
                        "Registrasi gagal: " + ex.getMessage());
            }
        });

        btnKembali.setOnAction(e -> tampilkanMenuUtama());

        VBox root = new VBox(20, lblJudul, grid, boxTombol);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 450, 450));
    }

    // ===== FORM LOGIN PENJUAL =====
    private void tampilkanFormLogin() {
        Label lblJudul = new Label("===== LOGIN PENJUAL =====");
        lblJudul.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblUsername = new Label("Username :");
        Label lblPassword = new Label("Password :");
        TextField txtUsername = new TextField();
        PasswordField txtPassword = new PasswordField();

        Button btnLogin = new Button("Login");
        Button btnKembali = new Button("Kembali");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(lblUsername, 0, 0);
        grid.add(txtUsername, 1, 0);
        grid.add(lblPassword, 0, 1);
        grid.add(txtPassword, 1, 1);

        HBox boxTombol = new HBox(10, btnLogin, btnKembali);
        boxTombol.setAlignment(Pos.CENTER);

        // ----- TAHAP 2: Event Handling -----
        btnLogin.setOnAction(e -> {
            String userLogin = txtUsername.getText().trim();
            String passLogin = txtPassword.getText().trim();

            // ----- TAHAP 3: Validasi kolom kosong -----
            if (userLogin.isEmpty() || passLogin.isEmpty()) {
                tampilkanAlert(AlertType.WARNING, "Validasi",
                        "Username dan password wajib diisi!");
                return;
            }

            if (penjual.login(userLogin, passLogin)) {
                tampilkanAlert(AlertType.INFORMATION, "Login",
                        "Login berhasil!\nSelamat datang, "
                        + penjual.getNama());
                tampilkanDashboard();
            } else {
                tampilkanAlert(AlertType.ERROR, "Login",
                        "Username atau password salah!");
            }
        });

        btnKembali.setOnAction(e -> tampilkanMenuUtama());

        VBox root = new VBox(20, lblJudul, grid, boxTombol);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 450, 450));
    }

    // ===== DASHBOARD BUZINICE =====
    private void tampilkanDashboard() {
        Label lblJudul = new Label("===== DASHBOARD BUZINICE =====");
        lblJudul.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label lblSelamat = new Label("Selamat datang, " + penjual.getNama());

        Button btnProfil = new Button("1. Profil Toko");
        Button btnLogout = new Button("2. Logout");
        btnProfil.setPrefWidth(200);
        btnLogout.setPrefWidth(200);

        // Area untuk menampilkan profil toko (awalnya kosong)
        GridPane gridProfil = new GridPane();
        gridProfil.setHgap(10);
        gridProfil.setVgap(6);
        gridProfil.setAlignment(Pos.CENTER);

        // ----- TAHAP 2: Event Handling -----
        btnProfil.setOnAction(e -> {
            gridProfil.getChildren().clear();
            gridProfil.add(new Label("===== PROFIL TOKO ====="), 0, 0, 2, 1);
            gridProfil.add(new Label("Nama Pemilik :"), 0, 1);
            gridProfil.add(new Label(penjual.getNama()), 1, 1);
            gridProfil.add(new Label("Username :"), 0, 2);
            gridProfil.add(new Label(penjual.getUsername()), 1, 2);
            gridProfil.add(new Label("Nama Toko :"), 0, 3);
            gridProfil.add(new Label(penjual.getNamaToko()), 1, 3);
            gridProfil.add(new Label("Kategori :"), 0, 4);
            gridProfil.add(new Label(penjual.getKategoriToko()), 1, 4);
        });

        btnLogout.setOnAction(e -> {
            tampilkanAlert(AlertType.INFORMATION, "Logout",
                    "Berhasil logout.");
            tampilkanMenuUtama();
        });

        VBox root = new VBox(15, lblJudul, lblSelamat, btnProfil, btnLogout,
                gridProfil);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 450, 450));
    }

    // =====================================================
    // TAHAP 3: ALERT (pop-up peringatan)
    // =====================================================
    private void tampilkanAlert(AlertType tipe, String judul, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}
