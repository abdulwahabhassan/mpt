package com.moniepoint.buildlogic

enum class BuildType(val applicationIdSuffix: String? = null) {
    DEBUG(applicationIdSuffix = ".debug"),
    RELEASE(applicationIdSuffix = ""),
}
