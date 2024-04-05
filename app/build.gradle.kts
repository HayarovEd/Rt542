plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.smartloanadvisornad"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smartloanadvisornad"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    /*signingConfigs {
        getByName("debug") {
            storeFile = file("ID4888.keystore")
            keyAlias = "mypass"
            storePassword = "loans.online.loan.app"
            keyPassword = "loans.online.loan.app"
        }
        create("release") {
            keyAlias = "mypass"
            keyPassword = "loans.online.loan.app"
            storeFile = file("ID4888.keystore")
            storePassword = "loans.online.loan.app"
            enableV2Signing = true
        }
    }*/

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.hilt.android)
    annotationProcessor (libs.google.hilt.compiler)
    kapt (libs.google.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    implementation (libs.accompanist.systemuicontroller)

    //Yandex
    implementation(libs.analytics)
    implementation (libs.mobmetricalib)
    //UserX
    //implementation(files("libs/UserX-4.2.2.aar"))


    //MyTracker
    implementation (libs.mytracker.sdk)

    //Appsflyer
    implementation (libs.af.android.sdk)

    //HMS
    implementation (libs.push)
    implementation (libs.hmscoreinstaller)
    implementation (libs.ads.identifier)
    implementation (libs.ads.installreferrer)


    //retrofit
    implementation (libs.gson)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Coil
    implementation (libs.coil.compose)

    // Icons
    implementation (libs.androidx.material.icons.extended)
}

kapt{
    correctErrorTypes = true
}