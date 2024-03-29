import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.iaiabot.furuhuru.exampleandroid"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            val githubApiKey: String =
                gradleLocalProperties(rootDir).getProperty("GITHUB_API_TOKEN")

            buildConfigField("String", "GITHUB_API_TOKEN", "\"" + githubApiKey + "\"")
        }
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "dev.iaiabot.furuhuru.exampleandroid"
}

dependencies {
    // 試すとき用
    //debugImplementation("dev.iaiabot.Furuhuru:Furuhuru:v0.9.0")
    implementation(project(":androidFuruhuru"))
    implementation(project(":core"))

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")

    val compose = "1.5.1"
    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.compose.material:material-icons-core:$compose")
    implementation("androidx.compose.material:material-icons-extended:$compose")
    implementation("androidx.compose.runtime:runtime-livedata:$compose")
    implementation("androidx.compose.runtime:runtime-rxjava2:$compose")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.7.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.browser:browser:1.6.0")
}
