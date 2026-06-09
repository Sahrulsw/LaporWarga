# 📱 LaporWarga - Smart City App (Updated)

Aplikasi pelaporan kerusakan fasilitas publik + cuaca real-time dengan **OpenStreetMap + Leaflet** (100% Gratis, tanpa Google Maps).

## 🎯 Fitur Utama

### 📍 Tab 1: LAPOR (Laporan Kerusakan)
- ✅ **GPS Location** - Real-time tracking
- ✅ **OpenStreetMap** - Interactive map (Leaflet)
- ✅ **Camera Intent** - Ambil foto laporan
- ✅ **Form Laporan** - Nama jalan, kategori, keterangan
- ✅ **Offline Cache** - Data tersimpan lokal
- ✅ **Background Service** - Upload dengan notification

### 🌤️ Tab 2: CUACA (Weather Dashboard)
- ✅ **Current Weather** - Real-time dari OpenWeatherMap API
- ✅ **OpenStreetMap** - Interactive weather map (Leaflet)
- ✅ **Weather Markers** - Pin dengan emoji di map
- ✅ **5-Day Forecast** - Prediksi cuaca
- ✅ **Search City** - Cari kota & auto-center
- ✅ **Favorite Cities** - Simpan kota favorit

### 📋 Tab 3: RIWAYAT (History)
- ✅ **RecyclerView** - Daftar semua laporan
- ✅ **Sorting** - By timestamp (newest first)
- ✅ **Detail View** - Click untuk melihat detail
- ✅ **Weather History** - Cuaca per lokasi
- ✅ **Delete Option** - Hapus laporan

## 🛠️ Tech Stack

- **Language**: Java
- **IDE**: Android Studio
- **Maps**: OpenStreetMap + Leaflet.js (via WebView)
- **Weather API**: OpenWeatherMap (Free Tier - Email only)
- **Database**: Room (SQLite) - Unified
- **HTTP**: Retrofit + Gson
- **Architecture**: MVVM + LiveData + ViewModel
- **Notifications**: Local push notifications
- **Background**: Service + Handler
- **Min SDK**: API 24+

## 🎨 UI/UX

- Material Design 3
- Gradient backgrounds
- Bottom Navigation (3 tabs)
- WebView for maps (Leaflet.js)
- Smooth animations
- Dark-friendly colors

## 📊 Project Structure

```
LaporWarga/
├── app/src/main/
│   ├── java/com/laporwarga/
│   │   ├── model/
│   │   │   ├── Laporan.java (Parcelable)
│   │   │   ├── CachedWeather.java
│   │   │   ├── FavoriteCity.java
│   │   │   ├── Weather.java
│   │   │   ├── MainWeather.java
│   │   │   ├── Wind.java
│   │   │   ├── Clouds.java
│   │   │   ├── SystemData.java
│   │   │   ├── ForecastResponse.java
│   │   │   ├── ForecastItem.java
│   │   │   └── WeatherResponse.java
│   │   │
│   │   ├── database/
│   │   │   ├── AppDatabase.java (UNIFIED - 3 tables)
│   │   │   ├── LaporanDao.java
│   │   │   ├── WeatherDao.java
│   │   │   ├── FavoriteCityDao.java
│   │   │   ├── UserPreferences.java
│   │   │   ├── CachedWeather.java
│   │   │   └── FavoriteCity.java
│   │   │
│   │   ├── api/
│   │   │   ├── WeatherApiService.java
│   │   │   └── RetrofitClient.java
│   │   │
│   │   ├── ui/
│   │   │   ├── activity/
│   │   │   │   ├── LoginActivity.java
│   │   │   │   ├── MainActivity.java (Bottom Nav Hub)
│   │   │   │   ├── DetailLaporanActivity.java
│   │   │   │   └── SearchCityActivity.java
│   │   │   │
│   │   │   ├── fragment/
│   │   │   │   ├── LaporanFragment.java (Tab 1)
│   │   │   │   ├── CuacaFragment.java (Tab 2)
│   │   │   │   ├── RiwayatFragment.java (Tab 3)
│   │   │   │   ├── MapFragmentLaporan.java (Leaflet - Lapor)
│   │   │   │   └── MapFragmentCuaca.java (Leaflet - Cuaca)
│   │   │   │
│   │   │   ├── adapter/
│   │   │   │   ├── LaporanAdapter.java
│   │   │   │   ├── ForecastAdapter.java
│   │   │   │   └── FavoriteCityAdapter.java
│   │   │   │
│   │   │   └── viewmodel/
│   │   │       ├── LaporanViewModel.java
│   │   │       ├── WeatherViewModel.java
│   │   │       └── SharedViewModel.java
│   │   │
│   │   ├── repository/
│   │   │   ├── LaporanRepository.java
│   │   │   ├── WeatherRepository.java
│   │   │   └── CombinedRepository.java
│   │   │
│   │   ├── service/
│   │   │   ├── UploadService.java
│   │   │   └── NetworkChangeReceiver.java
│   │   │
│   │   └── util/
│   │       ├── WeatherUtils.java
│   │       ├── LocationManager.java
│   │       ├── NotificationHelper.java
│   │       └── SharedPreferencesManager.java
│   │
│   ├── assets/
│   │   ├── leaflet_laporan.html (Map untuk Lapor)
│   │   └── leaflet_cuaca.html (Map untuk Cuaca)
│   │
│   └── res/
│       ├── layout/
│       │   ├── activity_login.xml
│       │   ├── activity_main.xml (Bottom Nav)
│       │   ├── activity_detail_laporan.xml
│       │   ├── fragment_laporan.xml
│       │   ├── fragment_cuaca.xml
│       │   ├── fragment_riwayat.xml
│       │   ├── fragment_map.xml
│       │   ├── item_laporan.xml
│       │   ├── item_forecast.xml
│       │   └── item_favorite_city.xml
│       │
│       ├── menu/
│       │   └── bottom_menu.xml
│       │
│       ├── values/
│       │   ├── strings.xml
│       │   └── colors.xml
│       │
│       └── xml/
│           └── file_paths.xml (FileProvider)
│
├── build.gradle
├── AndroidManifest.xml
└── README.md
```

## 📦 Dependencies

```gradle
dependencies {
    // AndroidX & Material
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    implementation 'androidx.webkit:webkit:1.7.0'
    
    // Room Database (UNIFIED)
    implementation 'androidx.room:room-runtime:2.5.2'
    annotationProcessor 'androidx.room:room-compiler:2.5.2'
    
    // Retrofit + OkHttp (untuk Weather API)
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.10.0'
    
    // Gson
    implementation 'com.google.code.gson:gson:2.10.1'
    
    // LiveData & ViewModel
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-runtime:2.6.1'
    
    // Location Services (untuk GPS)
    implementation 'com.google.android.gms:play-services-location:21.0.1'
    
    // Navigation (untuk Bottom Nav)
    implementation 'androidx.navigation:navigation-fragment:2.6.0'
    implementation 'androidx.navigation:navigation-ui:2.6.0'
}
```

## 🗺️ OpenStreetMap + Leaflet Info

### OpenStreetMap
- **URL**: https://www.openstreetmap.org
- **Tiles**: `https://tile.openstreetmap.org/{z}/{x}/{y}.png`
- **Cost**: 100% FREE ✅
- **API Key**: TIDAK PERLU ✅
- **Rate Limit**: Reasonable (~1 req/sec)

### Leaflet.js
- **Library**: JavaScript map library
- **Size**: ~42KB minified
- **Docs**: https://leafletjs.com
- **License**: BSD 2-Clause

### OpenWeatherMap API
- **URL**: https://openweathermap.org/api
- **Current Weather**: ✅ FREE
- **Forecast**: ✅ FREE
- **API Key**: Diperlukan (email saja, FREE)
- **Rate Limit**: 60 calls/minute

## 🚀 Quick Start (5 Menit)

### 1. Clone Repository
```bash
git clone https://github.com/Sahrulsw/LaporWarga.git
cd LaporWarga
```

### 2. Open di Android Studio
- File → Open → Select `LaporWarga`
- Tunggu Gradle Sync

### 3. Get OpenWeatherMap API Key (FREE)
```
1. https://openweathermap.org/api
2. Sign up dengan email
3. Confirm email
4. Copy API Key
5. Buka: app/src/main/java/com/laporwarga/repository/WeatherRepository.java
6. Ganti: private static final String API_KEY = "YOUR_API_KEY";
```

### 4. Run Project
```bash
# Connect device atau buka emulator
# Click Run (Shift + F10)
```

## 📱 Permissions

```xml
<!-- Internet -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- GPS Location -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

<!-- Camera -->
<uses-permission android:name="android.permission.CAMERA" />

<!-- Storage -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<!-- Notifications -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

<!-- Network -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 🎯 Fitur Detail

### Tab 1: LAPOR 📍

**Main Features:**
- Login dengan nama (SharedPreferences)
- GPS auto-detect lokasi
- OpenStreetMap interactive (Leaflet)
- Form laporan:
  - Nama jalan (text input)
  - Kategori (spinner: Jalan Berlubang, Lampu Mati, Sampah)
  - Keterangan (multiline text)
  - Foto (camera intent)
- Background service (3 detik delay)
- Push notification "Laporan Diterima!"
- Offline cache (Room database)

**Database:**
- Table: `laporan`
- Fields: id, latitude, longitude, jalan, kategori, keterangan, fotoUri, timestamp, status

### Tab 2: CUACA 🌤️

**Main Features:**
- Auto-detect city dari GPS
- Search city (SearchActivity)
- Current weather display:
  - Temperature (besar)
  - Feels like
  - Humidity
  - Wind speed
  - Description
- OpenStreetMap dengan weather markers (emoji)
- 5-day forecast (horizontal scroll)
- Favorite cities (save/delete)
- Pull-to-refresh

**Database:**
- Table 1: `cached_weather` - Cache cuaca
- Table 2: `favorite_city` - Kota favorit

### Tab 3: RIWAYAT 📋

**Main Features:**
- RecyclerView semua laporan
- Sorting: timestamp DESC (newest first)
- Click item → Detail view (Parcelable)
- Detail includes:
  - Foto
  - Lokasi (koordinat)
  - Kategori
  - Keterangan
  - Cuaca saat itu
  - Timestamp
- Delete option

## 📊 Database Schema

### Table: laporan
```sql
CREATE TABLE laporan (
    id INTEGER PRIMARY KEY,
    latitude REAL,
    longitude REAL,
    jalan TEXT,
    kategori TEXT,
    keterangan TEXT,
    fotoUri TEXT,
    timestamp LONG,
    status TEXT
)
```

### Table: cached_weather
```sql
CREATE TABLE cached_weather (
    id INTEGER PRIMARY KEY,
    cityName TEXT UNIQUE,
    temperature REAL,
    feelsLike REAL,
    humidity INTEGER,
    windSpeed REAL,
    description TEXT,
    icon TEXT,
    latitude REAL,
    longitude REAL,
    timestamp LONG
)
```

### Table: favorite_city
```sql
CREATE TABLE favorite_city (
    id INTEGER PRIMARY KEY,
    cityName TEXT UNIQUE,
    latitude REAL,
    longitude REAL,
    addedDate LONG
)
```

## 🔄 Data Flow

```
LAPOR TAB:
User Input (Form + Camera)
    ↓
Validasi data
    ↓
Room Database (insert)
    ↓
UploadService (background)
    ↓
Push Notification
    ↓
Update UI

CUACA TAB:
GPS/Search City
    ↓
WeatherRepository
    ↓
OpenWeatherMap API
    ↓
Json Parse (Gson)
    ↓
Room Cache
    ↓
LiveData → ViewModel
    ↓
UI Update (Fragment)
    ↓
WebView Leaflet Map

RIWAYAT TAB:
Room Query (getAllLaporan)
    ↓
LiveData observer
    ↓
RecyclerView adapter
    ↓
Click item → Detail Activity (Parcelable)
```

## 🔑 API Keys Needed

| Service | Key? | Cost | Signup |
|---------|------|------|--------|
| OpenWeatherMap | ✅ | FREE | Email only |
| OpenStreetMap | ❌ | FREE | None |
| Leaflet.js | ❌ | FREE | CDN only |

## ✅ Setup Checklist

- [ ] Clone repository
- [ ] Open di Android Studio
- [ ] Gradle sync
- [ ] Get OpenWeatherMap API Key
- [ ] Update API Key
- [ ] Grant permissions (GPS, Camera, Storage)
- [ ] Connect device/emulator
- [ ] Run project
- [ ] Test Tab 1 (Lapor)
- [ ] Test Tab 2 (Cuaca)
- [ ] Test Tab 3 (Riwayat)

## 🐛 Troubleshooting

### ❌ Map tidak muncul
```
✅ Check internet connection
✅ Check WebView rendering
✅ Verify leaflet HTML di assets
✅ Clear app data & restart
```

### ❌ Weather tidak load
```
✅ Verify API Key
✅ Check OpenWeatherMap status
✅ Check internet connection
✅ Check logcat errors
```

### ❌ GPS tidak jalan
```
✅ Enable location permission
✅ Enable GPS di device
✅ Move outdoor
✅ Wait 10-30 seconds
```

### ❌ Camera not opening
```
✅ Enable camera permission
✅ Check if camera available
✅ Check logcat errors
```

## 📚 Resources

- **OpenStreetMap**: https://www.openstreetmap.org
- **Leaflet**: https://leafletjs.com
- **Leaflet Tutorial**: https://leafletjs.com/examples.html
- **OpenWeatherMap**: https://openweathermap.org/api
- **Android WebView**: https://developer.android.com/guide/webapps/webview
- **Room Database**: https://developer.android.com/training/data-storage/room
- **Retrofit**: https://square.github.io/retrofit/
- **Android Location**: https://developer.android.com/training/location

## 🎨 Screenshots (Placeholder)

Tab 1 (Lapor): Map + Form + Camera  
Tab 2 (Cuaca): Weather info + Map + Forecast  
Tab 3 (Riwayat): List laporan + Detail

## 🚀 Future Enhancements

- [ ] Real backend API integration
- [ ] Admin dashboard (track laporan)
- [ ] Weather alerts & warnings
- [ ] Air quality index (AQI)
- [ ] Multiple language support
- [ ] Dark mode
- [ ] Offline maps caching
- [ ] Social sharing
- [ ] User ratings & feedback

## 💎 Advantages

✅ **100% Free** - No credit card needed  
✅ **OpenStreetMap** - Open source maps  
✅ **Offline Ready** - Cache data locally  
✅ **Lightweight** - Leaflet is only 42KB  
✅ **Privacy** - No Google tracking  
✅ **Unified App** - 2 features in 1 app  
✅ **Production Ready** - Full implementation  

## 📄 License

MIT License - Free to use & modify

## 👨‍💻 Author

Sahrulsw

---

**Status**: ✅ Active Development  
**Last Updated**: 2026-06-09  
**Version**: 2.0 (Merged)
