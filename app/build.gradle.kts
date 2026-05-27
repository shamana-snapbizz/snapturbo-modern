import com.android.build.api.dsl.ProductFlavor
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

    fun loadProperties(fileName: String): Properties {
        val props = Properties()
        val file = file("config/$fileName.properties")

        if (!file.exists()) {
            throw GradleException("Missing config file: ${file.path}")
        }

        file.inputStream().use {
            props.load(it)
        }
        return props
    }

    fun ProductFlavor.applyProperties(name: String) {
        val props = loadProperties(name)
        props.forEach { key, value ->
            buildConfigField(
                "String",
                key.toString(),
                "\"$value\""
            )
        }
    }


android {
    namespace = "com.snapbizz.snapturbo"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.snapbizz.snapturbo.modern"
        minSdk = 23
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(
                org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
            )
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    //Need to add mapper from the playstore crashlitics
    // -------------------- Product Flavors --------------------
    flavorDimensions += "env"

    productFlavors {
        create("development") {
            dimension = "env"
            versionCode = 1
            versionName = "1.0"
            versionNameSuffix = "( Test )"
            applyProperties("dev")
            buildConfigField("String", "APP_VERSION_PREFIX", "\"SnapTurbo_V\"")
//            resValue ("string", "env_name", "development")
        }

        create("production") {
            dimension = "env"
            versionCode = 1
            versionName = "1.0"
            versionNameSuffix = "( Prod )"
            applyProperties("prod")
            buildConfigField("String", "APP_VERSION_PREFIX", "\"SnapTurbo_V\"")
//            resValue  ("string", "env_name", "production")
        }
    }

}

dependencies {
    // Kotlin & Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)//
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.lifecycle.service)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Hilt DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // SQLCipher and SQLite Framework
    implementation(libs.sqlcipher)
    implementation(libs.androidx.sqlite)

    // Coroutines
    implementation(libs.coroutines.android)

    // Navigation (optional for Compose)
    implementation(libs.navigation.compose)

    // Coil (optional for images)
    implementation(libs.coil.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Retrofit + Gson
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)

    //loggin
    implementation(libs.timber)

    //datastore
    implementation(libs.androidx.datastore.preferences)


}