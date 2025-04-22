// PMD custom task
import org.gradle.api.plugins.quality.Pmd

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("io.gitlab.arturbosch.detekt")
    id("pmd")
}

android {
    namespace = "com.example.cryptocurrencyapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cryptocurrencyapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// PMD Configuration (outside android block)
pmd {
    toolVersion = "6.55.0"
    isConsoleOutput = true
    ruleSetFiles = files("config/pmd/ruleset.xml")
    ruleSets = listOf()
}

tasks.register<Pmd>("runPmd") {
    group = "verification"
    description = "Run PMD analysis"

    source = fileTree("src/main/java") {
        include("**/*.java")
        exclude(
            "**/test/**",
            "**/androidTest/**",
            "**/commonTest/**",
            "**/jvmTest/**",
            "**/androidUnitTest/**",
            "**/androidInstrumentedTest/**",
            "**/jsTest/**",
            "**/iosTest/**"
        )
    }

    ruleSetFiles = files("${rootDir}/config/pmd/ruleset.xml")
    ruleSets = listOf()

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.core.ktx.v1150)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.material.v178)
    implementation(libs.androidx.navigation.compose.v289)
    implementation(libs.retrofit)
    implementation(libs.converter.gson.v2110)
    implementation(libs.okhttp.v4120)
    implementation(libs.okhttp.logging)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.appcompat)
    kapt(libs.androidx.room.compiler)
    implementation(libs.hilt.android.v252)
    kapt(libs.hilt.android.compiler.v252)


    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose.v289)
    implementation(libs.accompanist.flowlayout)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v287)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Dagger - Hilt
    implementation(libs.hilt.android.v252)
    kapt(libs.hilt.android.compiler.v252)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit.v2110)
    implementation(libs.converter.gson.v2110)
    implementation(libs.okhttp.v500alpha14)
    implementation(libs.okhttp.logging)
}

kapt {
    correctErrorTypes = true
}