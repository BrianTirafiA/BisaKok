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

    }
}
//
//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
