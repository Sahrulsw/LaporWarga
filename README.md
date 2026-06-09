# 📱 LaporWarga - Smart City App

Aplikasi pelaporan kerusakan fasilitas publik secara real-time dengan fitur GPS, Camera, dan Database lokal.

## 🎯 Fitur Utama

- ✅ **Login & Session** - Persistent login dengan SharedPreferences
- ✅ **Maps & GPS** - Real-time location tracking dengan Google Maps
- ✅ **Camera Intent** - Ambil foto laporan langsung dari kamera
- ✅ **Database Lokal** - Room/SQLite untuk penyimpanan data
- ✅ **RecyclerView** - Daftar laporan dengan scrolling smooth
- ✅ **Background Service** - Upload dengan notification
- ✅ **Broadcast Receiver** - Monitor status jaringan
- ✅ **Parcelable** - Passing data antar Activity/Fragment

## 🛠️ Tech Stack

- **Language**: Java
- **IDE**: Android Studio
- **Database**: Room (SQLite)
- **Maps**: Google Maps SDK
- **Min SDK**: API 24+

## 🚀 Quick Start

### 1. Clone Repository
```bash
git clone https://github.com/Sahrulsw/LaporWarga.git
cd LaporWarga
```

### 2. Open in Android Studio
File → Open → Select LaporWarga folder

### 3. Configure Google Maps
- Get API Key from [Google Cloud Console](https://console.cloud.google.com)
- Replace `YOUR_API_KEY_HERE` in AndroidManifest.xml

### 4. Sync & Run
- Gradle Sync
- Run on device/emulator

## 📁 Project Structure

```
LaporWarga/
├── app/src/main/
│   ├── java/com/laporwarga/
│   │   ├── model/
│   │   ├── database/
│   │   ├── ui/
│   │   ├── service/
│   │   └── util/
│   └── res/
│       ├── layout/
│       ├── menu/
│       ├── values/
│       └── xml/
└── build.gradle
```

## 📱 Screens

1. **LoginActivity** - User authentication
2. **MainActivity** - Bottom navigation hub
3. **BerandaFragment** - Google Maps dengan GPS
4. **LaporanFragment** - Form laporan + Camera
5. **RiwayatFragment** - Daftar laporan (RecyclerView)
6. **DetailLaporanActivity** - Detail laporan

## 🔐 Permissions Required

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 📦 Dependencies

```gradle
dependencies {
    // AndroidX & Material
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    
    // Room Database
    implementation 'androidx.room:room-runtime:2.5.2'
    annotationProcessor 'androidx.room:room-compiler:2.5.2'
    
    // Google Maps & Location
    implementation 'com.google.android.gms:play-services-maps:18.1.0'
    implementation 'com.google.android.gms:play-services-location:21.0.1'
    
    // Navigation
    implementation 'androidx.navigation:navigation-fragment:2.6.0'
    implementation 'androidx.navigation:navigation-ui:2.6.0'
    
    // Lifecycle
    implementation 'androidx.lifecycle:lifecycle-runtime:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.6.1'
}
```

## 🔧 Installation Steps

### Step 1: Buat Package Structure
Kanan-klik pada `java/com/laporwarga` → New → Package:
- `model`
- `database`
- `ui.activity`
- `ui.fragment`
- `ui.adapter`
- `service`
- `util`

### Step 2: Copy Java Files
Copy semua file `.java` sesuai struktur folder

### Step 3: Copy XML Resources
Copy semua file XML ke folder `res/layout`, `res/menu`, `res/values`, `res/xml`

### Step 4: Update AndroidManifest.xml
Replace dengan manifest yang sudah disediakan

### Step 5: Update build.gradle
Tambahkan semua dependencies

### Step 6: Run Project
```bash
# Gradle sync
# Connect device/emulator
# Click Run button
```

## 🎨 Features Breakdown

### Login & Session
- User diminta input nama saat pertama kali
- Nama disimpan di SharedPreferences
- Saat app dibuka kembali, auto-login ke Dashboard

### Dashboard & Maps
- Bottom Navigation dengan 3 tab
- Tab Beranda menampilkan Google Maps
- Maps otomatis menunjukkan posisi GPS user
- Marker otomatis di koordinat user

### Report Form
- Input: Nama Jalan, Kategori, Keterangan
- Wajib ambil foto via camera
- Auto-capture GPS coordinates
- Simpan ke Room Database

### Upload & Notification
- Background Service delay 3 detik (simulasi upload)
- Push Notification "Laporan Diterima!"
- User bisa lanjut ke activity lain

### Report History
- RecyclerView menampilkan semua laporan
- Sorting by timestamp (newest first)
- Click item → Detail view dengan Parcelable

### Network Monitoring
- Broadcast Receiver monitor connectivity
- Toast "Jaringan Terputus!" saat WiFi/Data hilang
- Toast "Jaringan Terhubung" saat online kembali

## 🐛 Troubleshooting

### Maps tidak muncul
- Pastikan API Key sudah benar di AndroidManifest.xml
- Cek di Google Cloud Console apakah Maps SDK enabled

### Permission denied
- Android 6.0+ memerlukan runtime permission
- Code sudah handle ini di BerandaFragment dan LaporanFragment

### Database error
- Pastikan semua @Entity dan @Dao sudah benar
- Rebuild project: Build → Clean Project → Rebuild Project

## 📝 Notes

- Semua fitur sudah diimplementasikan dalam Java
- Architecture menggunakan LiveData & Room
- Thread-safe database operations
- Material Design UI

## 👨‍💻 Author

Sahrulsw

## 📄 License

MIT License
