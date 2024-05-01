plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.musicify"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.musicify"
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.annotation:annotation:1.7.1")

    implementation("com.google.android.material:material:1.11.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //implementation("androidx.compose.foundation:foundation-android:1.5.4")

    // Material UI
    //implementation("androidx.compose.material:material:1.6.5")
    implementation("androidx.compose.ui:ui:1.6.6")
    implementation("androidx.compose.ui:ui-util:1.6.6")
    implementation("androidx.compose.ui:ui-graphics:1.6.6")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.6")
    implementation("androidx.compose.material3:material3:1.2.1")


    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // GIF Loader
    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation("com.github.bumptech.glide:gifdecoder:4.14.2")
    implementation("io.coil-kt:coil:2.6.0")
    implementation("io.coil-kt:coil-gif:2.6.0")
    implementation("io.coil-kt:coil-compose:2.6.0")


    implementation("com.google.accompanist:accompanist-drawablepainter:0.32.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.20.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.20.0")

    // Music
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha")
    implementation ("androidx.media3:media3-exoplayer:1.3.1")

    // implementation("dev.rikka.rikkax.parcelablelist")

    // Font Awesome
    //implementation ("info.androidhive:fontawesome:0.0.5")
    //implementation ("com.android.support:appcompat-v7:28.0.0")
}
