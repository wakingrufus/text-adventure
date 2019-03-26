import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.3.21"
}


repositories {
    mavenCentral()
    jcenter()
}

kotlin {

    linuxX64("linux") {
        binaries {
            executable (listOf(RELEASE))
        }
    }
    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val linuxMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.1.1")
                implementation(project(":text-adventure-terminal"))
                implementation(project(":text-adventure-engine-common"))
                implementation(project(":my-game-common"))
            }
        }
        val linuxTest by getting {
            dependencies {
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}