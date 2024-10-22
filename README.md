Dokumentasi Proyek Vendor Management API
# Deskripsi Proyek
  Proyek ini adalah API yang digunakan untuk mengelola data vendor. API menyediakan berbagai fitur seperti registrasi pengguna, dan manajemen data vendor dengan mengimplementasikan keamanan menggunakan Spring Security dan pembatasan laju dengan Bucket4j.
# Prerequisites
  Sebelum Anda dapat menjalankan aplikasi, pastikan Anda memenuhi persyaratan berikut:
    •	Java Development Kit (JDK)
        - Versi yang direkomendasikan: Java 17 
    •	Apache Maven
        - Untuk mengelola dependensi dan menjalankan aplikasi.
        - Unduh dan instal Maven.
        - Pastikan Maven telah ditambahkan ke PATH Anda.
    •	MySQL dan XAMPP
        - Pastikan Anda telah menginstal MySQL melalui XAMPP.
        - Anda dapat mengunduh XAMPP dari situs resmi XAMPP.
        - Setelah menginstal, jalankan XAMPP Control Panel dan aktifkan modul MySQL.
# Menyiapkan Database
  Sebelum menjalankan aplikasi, Anda perlu menyiapkan database MySQL:
    1.	Buka phpMyAdmin:
    	Akses melalui browser dengan URL: http://localhost/phpmyadmin.
    2.	Buat Database:
        - Klik pada tab Databases.
        - Buat database baru, vendor_management.
# Langkah-Langkah untuk Menjalankan Aplikasi
  4.1. Clone Repository
    Clone repository proyek ke komputer lokal Anda menggunakan perintah git berikut:
    https://github.com/SuciPrasetyoBobyNugroho/vendor_management.git 
  4.2. Compile Proyek
    Setelah Anda berada di direktori proyek, jalankan perintah berikut untuk mengompilasi proyek:
    mvn clean install
    Perintah ini akan mengunduh semua dependensi yang diperlukan dan mengompilasi.
 4.3. Konfigurasi Aplikasi
    Pastikan Anda telah mengonfigurasi koneksi ke database MySQL di file application.properties. Berikut adalah contoh konfigurasi:
        spring.datasource.url=jdbc:mysql://localhost:3306/vendor_management
        spring.datasource.username=root
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
    Gantilah your_password dengan password MySQL Anda (jika Anda menggunakan password untuk user root).
 4.4. Jalankan Aplikasi
    Setelah berhasil dikompilasi, Anda dapat menjalankan aplikasi dengan perintah berikut:
        mvn spring-boot:run
    Setelah menjalankan perintah ini, Anda akan melihat log yang menunjukkan bahwa aplikasi telah berhasil dimulai.
# Dokumentasi API
  5.1. Endpoint Swagger
    Untuk memudahkan dokumentasi dan pengujian API, saya menyediakan Swagger UI. Untuk mengaksesnya, buka URL berikut di browser Anda:
        http://localhost:8080/swagger-ui/index.html
  5.2. Endpoint Utama
    Registrasi Pengguna
    •	URL: /api/users/register
    •	Method: POST
    •	Request Body:
    {
        "email": "test@example.com",
        "password": "password123"
    }
# Strategi Penerapan Rate Limit
    API ini menerapkan pembatasan laju menggunakan Bucket4j. Konfigurasi rate limiting ditetapkan di dalam RateLimiterConfig.java sebagai berikut:
        @Bean
        public Bucket bucket() {
            Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofSeconds(1)));
            return Bucket.builder().addLimit(limit).build();
        }
    •	Batasan: Pengguna hanya dapat melakukan 2 permintaan dalam 1 detik. Jika batas tersebut terlampaui, API akan mengembalikan status 429 Too Many Requests.
# Troubleshooting
    •	Jika Anda mengalami masalah saat menjalankan aplikasi, periksa log di terminal untuk informasi lebih lanjut tentang kesalahan.
    •	Pastikan semua dependensi telah diunduh dengan benar dan tidak ada kesalahan dalam konfigurasi.
    •	Jika Anda mendapatkan kesalahan 401 Unauthorized saat mengakses endpoint, pastikan Anda mengakses endpoint yang diizinkan tanpa autentikasi.
