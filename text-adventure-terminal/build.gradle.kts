import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    kotlin("multiplatform") version "1.3.20"
}

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm()
    linuxX64("linux")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":text-adventure-engine-common"))
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("kotlin-test-common"))
                implementation(kotlin("kotlin-test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("kotlin-test"))
                implementation(kotlin("kotlin-test-junit"))
            }
        }
        val linuxMain by getting {
            dependencies {
            }
        }
        val linuxTest by getting {
            dependencies {
            }
        }
    }
}