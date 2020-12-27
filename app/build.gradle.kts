plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId("fr.nihilus.xenobladechronicles")
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode(1)
        versionName("1.0.0")
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += arrayOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    kapt {
        correctErrorTypes = true
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
        }
    }

    sourceSets {
        // Add Room schemas to test sources in order to test database migrations.
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
    }
}

dependencies {
    val androidx_room = "2.2.6"
    val androidx_lifecycle = "2.2.0"
    val dagger = "2.30.1"

    val coroutines = "1.4.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.2.5")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.preference:preference-ktx:1.1.1")

    implementation("androidx.room:room-ktx:$androidx_room")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$androidx_lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$androidx_lifecycle")

    implementation("com.google.dagger:dagger:$dagger")
    implementation("com.google.dagger:dagger-android:$dagger")
    implementation("com.google.dagger:dagger-android-support:$dagger")

    kapt("com.google.dagger:dagger-android-processor:$dagger")
    kapt("com.google.dagger:dagger-compiler:$dagger")
    kapt("androidx.room:room-compiler:$androidx_room")

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.hamcrest:hamcrest-all:1.3")

    androidTestImplementation("android.room:room-testing:$androidx_room")
}
