import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    kotlin("multiplatform") version "1.3.11"
}

repositories {
    mavenCentral()
    jcenter()
}

kotlin.apply {
    targets.add(presets["linuxX64"].createTarget("linux"))

    sourceSets {
        operator fun String.invoke(action: KotlinSourceSet.() -> Unit) {
            named(this, action)
        }
        "commonMain" {
            dependencies {
                implementation(project(":text-adventure-engine-common"))
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
            }
        }

        "commonTest" {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
            }
        }
    }
}