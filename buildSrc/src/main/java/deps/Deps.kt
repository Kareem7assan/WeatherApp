package deps

import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

object Deps {
    object gradle {
        const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    }


    object dagger_hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
        const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
        const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"


    }

    object kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        //const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    }


    object test {
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:${Versions.junit}"
        const val junitAndroid = "androidx.test.ext:junit:${Versions.junitAndroid}"

        const val roboElectric = "androidx.test:core:${Versions.roboElectric}"
        const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
        const val archTest = "androidx.arch.core:core-testing:${Versions.archTest}"
        const val truthTest = "com.google.truth:truth:${Versions.truth}"


        const val espressoAndroid =
            "androidx.test.espresso:espresso-core:${Versions.espressoAndroid}"
    }

    object android {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val ktx = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"

    }
    object jetpack{
        // ViewModel and LiveData
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle_version}"
        const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
        const val room = "androidx.room:room-runtime:${Versions.lifecycle_version}"
        const val compilerRoom = "androidx.room:room-compiler:${Versions.lifecycle_version}"
        const val testRoom = "androidx.room:room-testing:${Versions.lifecycle_version}"

    }

    object androidx {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        object navigation {
            const val runtime = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
            const val safeArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        }
    }
    object rx{
        const val rxjava = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxjava}"
        const val rxKotlin = "com.github.ReactiveX:RxKotlin:${Versions.rxKotlin}"
        const val rxBindingMaterial = "com.jakewharton.rxbinding4:rxbinding-material:${Versions.rxBinding}"
        const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBinding}"

        const val adapter_rxjava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.com_squareup_retrofit2}"
    }

    object ktx{
        const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
        const val ktx_activity= "androidx.preference:preference-ktx:${Versions.ktxKotlin}"

    }

    object anko{
        const val anko = "org.jetbrains.anko:anko:${Versions.anko}"

    }

    object retrofit{
        const val retrofit_lib = "com.squareup.retrofit2:retrofit:${Versions.com_squareup_retrofit2}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.com_squareup_retrofit2}"
        const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3}"
    }

    object coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object google {
        const val services = "com.google.gms:google-services:${Versions.googleServices}"
        const val firebaseAnalytics =
            "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
        const val crashlyticsPlugin =
            "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsPlugin}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.crashlytics}"
        const val performance = "com.google.firebase:firebase-perf:${Versions.performance}"
        const val performancePlugin =
            "com.google.firebase:perf-plugin:${Versions.performancePlugin}"
        const val playAuth = "com.google.android.gms:play-services-auth:${Versions.playAuth}"
        const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    }

    object dagger {
        const val daggerCore = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val daggerAndroidSupport =
            "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    const val vkSdk = "com.vk:androidsdk:${Versions.vkSdk}"
    const val gradleVersionsPlugin =
        "com.github.ben-manes:gradle-Versions-plugin:${Versions.gradleVersionsPlugin}"
    const val facebook = "com.facebook.android:facebook-android-sdk:${Versions.facebook}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val crop = "com.github.yalantis:ucrop:${Versions.crop}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

fun addRepos(repositoryHandler: RepositoryHandler) {
    repositoryHandler.google()
    repositoryHandler.jcenter()
    repositoryHandler.maven {
        url = URI.create("https://oss.sonatype.org/content/repositories/snapshots")
    }
    repositoryHandler.maven { url = URI.create("https://jitpack.io") }
}
