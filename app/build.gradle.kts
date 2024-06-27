plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    alias(libs.plugins.compilerKsp)
}

android {
    namespace = "com.example.afisha"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.afisha"
        minSdk = 24
        targetSdk = 34
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

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.ksp)
    implementation(libs.adapter.rxjava3)
    implementation(libs.glide)
    implementation(libs.gson)
    implementation(libs.picasso)
    implementation(libs.retrofit)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation("androidx.room:room-rxjava3:2.5.0")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}