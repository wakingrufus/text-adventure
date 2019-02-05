plugins {
    kotlin("multiplatform") version "1.3.20"
}

repositories {
    mavenCentral()
    jcenter()
}

kotlin.apply {
    targets.add(presets["jvm"].createTarget("jvm"))
    targets.add(presets["linuxX64"].createTarget("linux"))

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":text-adventure-engine-common"))
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