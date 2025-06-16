import com.moniepoint.buildlogic.configureDesign

plugins {
    id("com.moniepoint.convention.core")
}

android {
    namespace = "com.moniepoint.core.designsystem"
    configureDesign(this)
}
