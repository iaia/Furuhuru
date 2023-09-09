plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 32

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "dev.iaiabot.furuhuru.android"
}

dependencies {
    implementation(project(":core"))
    val ktor_version = "2.0.3"
    val koin_version = "3.4.3"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.insert-koin:koin-android:$koin_version")

    implementation("androidx.compose.ui:ui:1.5.1")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.5.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.5.1")
    // Material Design
    implementation("androidx.compose.material:material:1.5.1")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.5.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.1")
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.7.2")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.5.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
