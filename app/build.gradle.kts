plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.portafolio.appdivisas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.portafolio.appdivisas"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /*----------------------------------------------------------------------*/
    //Gson
    implementation("com.google.code.gson:gson:2.8.5")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Gson convert
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Picasso
    implementation ("com.squareup.picasso:picasso:2.8")
    //Graficos
    // Holograph
    implementation ("org.quanqi:android-holo-graph:0.1.0")
    /*----------------------------------------------------------------------*/
}