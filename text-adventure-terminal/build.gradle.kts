
plugins {
    kotlin("multiplatform") version "1.3.21"
}

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm {
        val main by compilations.getting {
            kotlinOptions {
                // Setup the Kotlin compiler options for the 'main' compilation:
                jvmTarget = "1.8"
            }
        }
    }
    linuxX64("linux"){
        binaries {
            sharedLib()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":text-adventure-engine-common"))
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.1.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
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