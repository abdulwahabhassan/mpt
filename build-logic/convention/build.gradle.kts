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
        register("moniePointApplicationConvention") {
            id = "com.moniepoint.convention.application"
            implementationClass = "MoniePointApplicationConventionPlugin"
        }
        register("moniePointCoreConvention") {
            id = "com.moniepoint.convention.core"
            implementationClass = "MoniePointCoreConventionPlugin"
        }
        register("moniePointFeatureConvention") {
            id = "com.moniepoint.convention.feature"
            implementationClass = "MoniePointFeatureConventionPlugin"
        }
    }
}
