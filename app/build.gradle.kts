plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.technogenis.iotbasedsmartwater"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.technogenis.iotbasedsmartwater"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation (platform(libs.firebase.bom))
    implementation (libs.firebase.analytics)

    // glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    implementation (libs.picasso)
    implementation (libs.circleimageview)
    implementation (libs.recyclerview.selection)
    implementation (libs.recyclerview)
    implementation (libs.cardview)
    implementation (libs.firebase.database)
    implementation (libs.firebase.ui.database)
}