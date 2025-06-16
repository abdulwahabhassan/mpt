plugins {
    id("com.moniepoint.convention.core")
}

android {
    namespace = "com.moniepoint.core.domain"
}

dependencies {
    api(project(":core:data"))
}
