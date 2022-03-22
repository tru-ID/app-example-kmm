plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "id.tru.kmm.phonecheckexample.android"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
var kotlinCoVersion = "1.6.0"
val kotlinVersion = "1.6.10"
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
}