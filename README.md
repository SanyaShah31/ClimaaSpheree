# 🌦️ ClimaaSpheree – Android Weather Applications

**ClimaaSpheree** is a collection of Android weather applications developed using **Kotlin** and **MVVM architecture**.  
The suite contains two connected applications that demonstrate **project evolution**, **feature expansion**, and **modular Android development**.

---

## 📱 Applications Overview

### 🔹 1. ClimaSpheree – Full Featured Weather App
ClimaSpheree is the primary and fully featured weather application.  
It focuses on delivering **real-time, hourly, and detailed weather information** along with user-based features.

#### 🚀 Features (ClimaSpheree)
- 🌍 Live real-time weather updates  
- ⏰ Hourly live forecast (8 AM – 10 AM, and next 4 time slots) 
- 🌡️ Temperature (current, high & low , C/F selection)  
- 🌧️ Rain probability  
- 💧 Humidity levels  
- 🌬️ Wind speed 
- 🌄 Sunrise & sunset timings  
- 📆 Live date & day display  
- 📍 Location-based weather 

#### 🔍 Search & User Features
- 🔎 City search with search history
- ❤️ Favorite cities management  
- 🔐 Firebase Authentication (Login / Logout , OTP Verification , Reset Password with mail)  
- 📧 Displays logged-in user email ID  
- 📢 Test ads integration (AdMob)  

#### 🌟 Advanced Integrations
- 🗺️ **Interactive Google Maps Integration** (`MapActivity`) – Users can click anywhere on the map to place a pinpoint marker and fetch weather details (temperature, description, wind speed) for those coordinates via Geocoding APIs.
- 📰 **Climate & Weather News Portal** (`NewsActivity`) – Fetches, searches, and displays real-time weather, climate, rainfall, and storm news using NewsAPI and OkHttp.
- 🔔 **Weather Alerts History & Notification Preferences** (`AlertsActivity`) – Local repository allowing users to view alert notifications, delete individual alerts (via long-press confirmation), clear all alerts, or toggle alerts on/off.
- 🌓 **Persistent Customization & Preferences** (`ProfileSettingsActivity`) – Sky Blue (Light Theme) and Navy Blue (Dark Theme) persisted using Jetpack DataStore Preferences along with temperature unit configurations.
- 🔄 **Background Sync Workers** (`WeatherUpdateWorker`) – Implements Jetpack WorkManager to perform periodic background weather forecast synchronization.
- ✉️ **Push Alerts (FCM)** (`MyFirebaseMessagingService`) – Receives data and notification payloads from Firebase Cloud Messaging and displays them locally if notifications are enabled.

---

### 🔹 2. ClimaSphere2 – 5-Day Weather Forecast App
ClimaSphere2 is a simplified and optimized version of the app, designed specifically for **future weather forecasting**.  
It focuses **only on providing accurate 5-day live weather forecasts** with a clean and lightweight UI.

#### 🚀 Features (ClimaSphere2)
- 📅 **5-day live weather forecast**  
- 🌡️ Daily temperature (min & max)  
- 🌧️ Rain probability  
- 💧 Humidity levels  
- 🌬️ Wind speed information  

---

## 🔗 Project Relationship & Cross-App Navigation
- **ClimaSpheree** → Full-featured application  
  - Real-time + hourly weather  
  - User authentication, ads, search history, interactive map, alerts dashboard, and detailed insights  

- **ClimaSphere2** → Forecast-focused application  
  - Only 5-day live forecast  

- **Cross-App Communication Protocol**: ClimaSpheree integrates and navigates directly with ClimaSphere2 using explicit Android Intents. Clicking the "Forecast" or "Next 7 Days" views in the dashboard or today's screen triggers an Intent redirecting to `com.example.climasphere2.TomorrowActivity` passing the active `city_name` bundle. This highlights **learning progression, feature separation, and real-world app versioning/modularization**, which is valuable for interviews and recruiters.

---

## 🛠️ Tech Stack (Common)
- **Language:** Kotlin  
- **Architecture:** MVVM  
- **UI:** XML layouts, Material Design, Lottie Animations  
- **API:** OpenWeatherMap REST API, NewsAPI  
- **Networking:** Retrofit, OkHttp, Coroutines  
- **Backend:** Firebase Authentication, Realtime Database, Cloud Messaging (FCM)  
- **Storage:** Jetpack DataStore Preferences  
- **Maps:** Google Maps SDK, Geocoder API  
- **Ads:** Google AdMob (Test Ads – ClimaaSpheree)  
- **Background Jobs:** Jetpack WorkManager  
- **Tools:** Android Studio

---

## 📂 Common Project Structure
The directories showcase clean separation of concerns and package alignment:
- `adapters/` – RecyclerView adapters (AlertsAdapter, FavoritesAdapter, NewsAdapter, SearchHistoryAdapter)  
- `api/` – API interfaces (NewsApiService)  
- `data/local/` – Local persistence managers (FavoritesManager, SettingsManager using DataStore)  
- `models/` – Data models (ForecastViewModel, NewsArticle, NewsResponse)  
- `network/` – Retrofit client instances (RetrofitInstance, ForecastRepository, ForecastResponse, CurrentWeatherResponse, WeatherApiService)  
- `repository/` – Data handling logic  
- `viewmodel/` – ViewModels  
- `utils/` – Utility & helper classes (NotificationHelper, NotificationPref, SearchHistoryPref, TemperatureUnitManager)

---

## 📋 Dependency Configuration (Gradle Specs)
### ClimaSpheree (App 1)
- **Compile SDK / Target SDK:** 36
- **Min SDK:** 24
- **JVM Target:** 17 (Java 17 compatibility)
- **Core Dependencies:**
  - `androidx.core:core-ktx:1.13.1`
  - `androidx.appcompat:appcompat:1.6.1`
  - `com.google.android.material:material:1.12.0`
  - `com.airbnb.android:lottie:6.1.0`
  - `com.google.firebase:firebase-bom:33.1.2` (Authentication, Cloud Messaging, Realtime Database, Analytics)
  - `androidx.work:work-runtime-ktx:2.8.1`
  - `androidx.datastore:datastore-preferences:1.1.0`
  - `com.google.android.gms:play-services-maps:18.1.0`
  - `com.google.android.gms:play-services-location:21.0.1`
  - `com.google.android.gms:play-services-ads:23.0.0`
  - `com.squareup.retrofit2:retrofit:2.9.0`
  - `com.squareup.okhttp3:okhttp:4.12.0`
  - `com.github.bumptech.glide:glide:4.16.0`

### ClimaSphere2 (App 2)
- **Compile SDK / Target SDK:** 36
- **Min SDK:** 24
- **JVM Target:** 11 (Java 11 compatibility)
- **Core Dependencies:**
  - `com.squareup.retrofit2:retrofit:2.9.0`
  - `com.airbnb.android:lottie:6.0.0`
  - `androidx.work:work-runtime-ktx:2.9.0`
  - `com.google.firebase:firebase-messaging:23.4.1`

---

## 🚀 Getting Started & Setup
### 1. Initialize Git Repository
To quickly set up the local git history and stage all workspace changes for deployment, a helper batch script is provided:
```bash
# Run the batch file in the repository root directory
./prepare_git.bat
```
This script will initialize git (if not already done), set the branch name to `main`, and commit all components automatically.

### 2. Open in Android Studio
1. Open Android Studio.
2. Select **File -> Open** and navigate to the project directory:
   - For App 1: Open the `ClimaaSpheree` directory.
   - For App 2: Open the `climasphere2` directory.
3. Allow Gradle to sync dependencies.

### 3. API Key Setup
The applications use:
- **OpenWeatherMap API Key**: Placed inside `TodayActivity.kt` and `TomorrowActivity.kt` to fetch real-time forecasts.
- **NewsAPI Key**: Stored in `NewsActivity.kt` to load weather-related articles.
- **Google Maps SDK Key**: Must be placed in the `AndroidManifest.xml` meta-data section under `com.google.android.geo.API_KEY` to enable the map rendering capability.
