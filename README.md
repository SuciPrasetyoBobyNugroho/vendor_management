# Dokumentasi Proyek Vendor Management API

## 1. Deskripsi Proyek
Proyek ini adalah API yang digunakan untuk mengelola data vendor. API menyediakan berbagai fitur seperti registrasi pengguna, dan manajemen data vendor dengan mengimplementasikan keamanan menggunakan Spring Security dan pembatasan laju dengan Bucket4j.

## 2. Prerequisites
Sebelum Anda dapat menjalankan aplikasi, pastikan Anda memenuhi persyaratan berikut:

- **Java Development Kit (JDK)**:
  - Versi yang direkomendasikan: Java 17.

- **Apache Maven**:
  - Untuk mengelola dependensi dan menjalankan aplikasi.
  - Unduh dan instal Maven.
  - Pastikan Maven telah ditambahkan ke PATH Anda.

- **MySQL dan XAMPP**:
  - Pastikan Anda telah menginstal MySQL melalui XAMPP.
  - Anda dapat mengunduh XAMPP dari [situs resmi XAMPP](https://www.apachefriends.org/index.html).
  - Setelah menginstal, jalankan XAMPP Control Panel dan aktifkan modul MySQL.

## 3. Menyiapkan Database
Sebelum menjalankan aplikasi, Anda perlu menyiapkan database MySQL:

1. **Buka phpMyAdmin**:
   - Akses melalui browser dengan URL: `http://localhost/phpmyadmin`.

2. **Buat Database**:
   - Klik pada tab Databases.
   - Buat database baru, `vendor_management`.

## 4. Langkah-Langkah untuk Menjalankan Aplikasi

### 4.1. Clone Repository
Clone repository proyek ke komputer lokal Anda menggunakan perintah git berikut: <br>
git clone https://github.com/SuciPrasetyoBobyNugroho/vendor_management.git 


### 4.2. Compile Proyek
Setelah Anda berada di direktori proyek, jalankan perintah berikut untuk mengompilasi proyek: <br>
mvn clean install <br>
Perintah ini akan mengunduh semua dependensi yang diperlukan dan mengompilasi.<br>

### 4.3. Konfigurasi Aplikasi
Pastikan Anda telah mengonfigurasi koneksi ke database MySQL di file `application.properties`. Berikut adalah contoh konfigurasi:
spring.datasource.url=jdbc:mysql://localhost:3306/vendor_management <br>
spring.datasource.username=root <br>
spring.datasource.password=your_password <br>
spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true <br>

Gantilah your_password dengan password MySQL Anda (jika Anda menggunakan password untuk user root).

### 4.4. Jalankan Aplikasi
Setelah berhasil dikompilasi, Anda dapat menjalankan aplikasi dengan perintah berikut: <br>
mvn spring-boot:run <br>
Setelah menjalankan perintah ini, Anda akan melihat log yang menunjukkan bahwa aplikasi telah berhasil dimulai.<br>

## 5. Dokumentasi API

### 5.1. Endpoint Swagger
Untuk memudahkan dokumentasi dan pengujian API, saya menyediakan Swagger UI. Untuk mengaksesnya, buka URL berikut di browser Anda:<br>
http://localhost:8080/swagger-ui/index.html <br>

## 5.2. Endpoint Utama
Registrasi Pengguna <br>
URL: /api/users/register <br>
Method: POST <br>
Request Body: <br>
{ <br>
    "email": "test@example.com", <br>
    "password": "password123" <br>
} <br>

## 6. Strategi Penerapan Rate Limit
API ini menerapkan pembatasan laju menggunakan Bucket4j. Konfigurasi rate limiting ditetapkan di dalam RateLimiterConfig.java sebagai berikut: <br>
@Bean <br>
public Bucket bucket() { <br>
    Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofSeconds(1))); <br>
    return Bucket.builder().addLimit(limit).build(); <br>
} <br>
•	Batasan: Pengguna hanya dapat melakukan 2 permintaan dalam 1 detik. Jika batas tersebut terlampaui, API akan mengembalikan status 429 Too Many Requests. <br>

## 7. Troubleshooting
•	Jika Anda mengalami masalah saat menjalankan aplikasi, periksa log di terminal untuk informasi lebih lanjut tentang kesalahan. <br>
•	Pastikan semua dependensi telah diunduh dengan benar dan tidak ada kesalahan dalam konfigurasi. <br>
•	Jika Anda mendapatkan kesalahan 401 Unauthorized saat mengakses endpoint, pastikan Anda mengakses endpoint yang diizinkan tanpa autentikasi. <br>

