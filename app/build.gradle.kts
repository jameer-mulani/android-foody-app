plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.foodyappstefan2023"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.foodyappstefan2023"
        minSdk = 21
        targetSdk = 33
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
        dataBinding = true
    }

    packagingOptions {
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/NOTICE.md")
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //coordinator layout
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")

    //Material design
    implementation("com.google.android.material:material:1.9.0")

    //Databinding
    implementation("com.android.databinding:compiler:3.2.0-alpha10")
    kapt("androidx.databinding:databinding-common:8.0.0")

    //DataStore preferences
    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")

    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    //Navigation component
    val navVersion = "2.4.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Room
    val roomVersion = "2.4.3"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") //for kotling ext & coroutine support in Room
    kapt("androidx.room:room-compiler:$roomVersion")

    //Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.7.2")

    //Dagger-hilt
    val daggerHiltVersion = "2.47"
    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")

    val hiltLifecycleViewModel = "1.0.0-alpha03"
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$hiltLifecycleViewModel")
    kapt("androidx.hilt:hilt-compiler:$hiltLifecycleViewModel")

    //Coroutines
    val kotlinCoroutineVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutineVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutineVersion")


    //Lifecycle ktx
    val lifecycleViewModelAndLifecycle = "2.4.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleViewModelAndLifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleViewModelAndLifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleViewModelAndLifecycle")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Coil- Image loading library
    implementation("io.coil-kt:coil:2.4.0")


    //Gson - Serialization library
    implementation("com.google.code.gson:gson:2.10.1")


    //Shimmer - library for shimmer effect
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
//    implementation ("com.todkars:shimmer-recyclerview:0.4.0")

    //Jsoup - HTML tag parsing library
    implementation("org.jsoup:jsoup:1.16.1")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}