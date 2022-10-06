plugins {
    id("com.android.library")
    kotlin("android")
}

val PUBLISH_ARTIFACT_ID: String = "android-furufuru"

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
}

dependencies {
    implementation(project(":core"))
    val ktor_version = "2.0.3"
    val koin_version = "3.2.0"

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")

    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.insert-koin:koin-android:$koin_version")

    implementation("androidx.compose.ui:ui:1.2.0")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.2.0")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.2.0")
    // Material Design
    implementation("androidx.compose.material:material:1.2.0")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.2.0")
    implementation("androidx.compose.material:material-icons-extended:1.2.0")
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.5.1")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.2.0")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

// apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")
