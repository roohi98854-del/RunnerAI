plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.kapt")
}
android {
    namespace = "com.example.runnerai"
    compileSdk = 35

    buildFeatures { buildConfig = true }

    defaultConfig {
        applicationId = "com.example.runnerai"
        minSdk = 26
        targetSdk = 35
        versionCode = 10
        versionName = "0.6.0"
    }

    flavorDimensions += "access"
    productFlavors {
        create("user") {
            dimension = "access"
            buildConfigField("boolean", "DEVELOPER_VIP", "false")
        }
        create("developer") {
            dimension = "access"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"
            dimension = "access"
            buildConfigField("boolean", "DEVELOPER_VIP", "true")
        }
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui:1.7.8")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.8")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.8")
}
