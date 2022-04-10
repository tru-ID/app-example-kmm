plugins {
    id("com.android.application")
    kotlin("android")
}
//def props = new Properties()
//file("tru.properties").withInputStream { props.load(it) }

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "id.tru.kmm.phonecheckexample.android"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        // Consult the README on instructions for setting up SERVER_BASE_URL
//        buildConfigField("String", "SERVER_BASE_URL", props.getProperty("EXAMPLE_SERVER_BASE_URL"))

//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    dataBinding {
        android.buildFeatures.dataBinding = true
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
    // Retrofit and relevant converters
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    // Kotlin
    implementation ("androidx.core:core-ktx:1.5.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
    // Animated check view
    implementation ("com.github.cdflynn:checkview:v1.1")
    // libphonenumber
    implementation ("com.googlecode.libphonenumber:libphonenumber:8.12.25")
    // Retrofit and relevant converters
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Testing
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")

}
//def getServerBaseUrl() {
//    return project.findProperty("SERVER_BASE_URL")
//}