import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.sqlDelight)
}


android {

    namespace = "dev.skumar.vridblog.service.persistence"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

}


dependencies {

    implementation(project(":core:domain"))
    implementation(project(":feature:blog"))

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)

    implementation(libs.bundles.sqlDelight)


    testImplementation(libs.junit)


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}



sqldelight {
    databases {
        create("VridBlogDatabase") {
            packageName.set("dev.skumar.vridblog.service.persistence.db")
        }
    }
}