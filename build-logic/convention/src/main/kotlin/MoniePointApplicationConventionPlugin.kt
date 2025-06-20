import com.android.build.api.dsl.ApplicationExtension
import com.moniepoint.buildlogic.configureDesign
import com.moniepoint.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class MoniePointApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = 35
                configureKotlinAndroid(this)
                configureDesign(this)
            }
            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":feature:home"))
                add("implementation", project(":feature:calculate"))
                add("implementation", project(":feature:estimate"))
                add("implementation", project(":feature:shipment"))
                add("implementation", project(":feature:profile"))

            }
        }
    }
}