package com.moniepoint.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.kapt")
        apply("dagger.hilt.android.plugin")
        apply("kotlinx-serialization")
    }

    commonExtension.apply {
        compileSdk = 35
        defaultConfig {
            minSdk = 23
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true
        }
        buildFeatures {
            buildConfig = true
            viewBinding = true
            aidl = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
        packaging {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }

        extensions.configure<KotlinAndroidProjectExtension> {
            jvmToolchain(17)
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
        add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
        add("implementation", libs.findLibrary("kotlinx.datetime").get())
        add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
        add("implementation", libs.findLibrary("hilt.android").get())
        add("kapt", libs.findLibrary("hilt.android.compiler").get())
        add("kapt", libs.findLibrary("hilt.compiler").get())
        add("implementation", libs.findLibrary("androidx.work.runtime.ktx").get())
        add("implementation", libs.findLibrary("hilt.work").get())
    }
}