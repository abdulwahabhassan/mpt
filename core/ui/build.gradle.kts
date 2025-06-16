import com.moniepoint.buildlogic.configureDesign

plugins {
    id("com.moniepoint.convention.core")
}

android {
    namespace = "com.moniepoint.core.ui"
    configureDesign(this)
}

dependencies {
    api(project(":core:designsystem"))
    implementation(project(":core:model"))
    api(libs.accompanist.permissions)
}
