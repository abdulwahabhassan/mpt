import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("moniepointApplicationConvention") {
            id = "com.moniepoint.convention.application"
            implementationClass = "MoniepointApplicationConventionPlugin"
        }
        register("moniepointCoreConvention") {
            id = "com.moniepoint.convention.core"
            implementationClass = "MoniepointCoreConventionPlugin"
        }
        register("moniepointFeatureConvention") {
            id = "com.moniepoint.convention.feature"
            implementationClass = "MoniepointFeatureConventionPlugin"
        }
    }
}
