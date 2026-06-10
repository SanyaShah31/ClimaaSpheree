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
- ⏰ Hourly live forecast (8 AM – 10 AM) 
- 🌡️ Temperature (current, high & low , C/F)  
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

## 🔗 Project Relationship
- **ClimaSpheree** → Full-featured application  
  - Real-time + hourly weather  
  - User authentication, ads, history, and detailed insights  

- **ClimaSphere2** → Forecast-focused application  
  - Only 5-day live forecast  

This structure highlights **learning progression, feature separation, and real-world app versioning**, which is valuable for interviews and recruiters.

---

## 🛠️ Tech Stack (Common)
- **Language:** Kotlin  
- **Architecture:** MVVM  
- **UI:** XML, Material Design  
- **API:** OpenWeatherMap REST API  
- **Networking:** Retrofit, Coroutines  
- **Backend:** Firebase Authentication (ClimaaSpheree)  
- **Ads:** Google AdMob (Test Ads – ClimaaSpheree)  
- **Tools:** Android Studio

---

## 📂 Common Project Structure
- `adapters/` – RecyclerView adapters  
- `api/` – API interfaces  
- `models/` – Data models  
- `network/` – Retrofit & API client  
- `repository/` – Data handling logic  
- `viewmodel/` – ViewModels  
- `utils/` – Utility & helper classes
