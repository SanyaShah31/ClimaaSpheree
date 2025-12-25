plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.climasphere2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.climasphere2"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

// Lottie (for animations)
    implementation ("com.airbnb.android:lottie:6.0.0")

// Material UI
    implementation ("com.google.android.material:material:1.10.0")

// RecyclerView (if needed)
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

// ConstraintLayout
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")

// Kotlin Stdlib (auto-added usually)
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

// Glide (optional if you load icons dynamically)
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

// WorkManager (you already use it)
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

// Firebase Messaging (you already use it)
    implementation ("com.google.firebase:firebase-messaging:23.4.1")

}