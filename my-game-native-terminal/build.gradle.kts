import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    kotlin("native") version "1.3.11"
  //  id("org.jetbrains.kotlin.native") version "1.3.11"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(project(":text-adventure-terminal"))
}
components["main"] {
    targets = listOf("linux_x64")
}
sourceSets["main"].component {
    target("linux_x64")
}
sourceSets["main"].apply {
    if (this is KotlinNativeMainComponent) {
        outputKinds.add(OutputKind.KLIBRARY)
        outputKinds.add(OutputKind.EXECUTABLE)
    }
}