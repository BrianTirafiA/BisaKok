plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply true // Aktifkan plugin google services
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Plugin yang diperlukan untuk Firebase dan Google Services
        classpath("com.google.gms:google-services:4.4.2") // Pastikan ini versi terbaru
    }
}
//
//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
