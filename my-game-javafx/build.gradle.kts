import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "1.3.21"
    jacoco
    application

}
buildscript{
    repositories {
                mavenCentral()
    }
    dependencies {
        classpath("de.dynamicfiles.projects.gradle.plugins:javafx-gradle-plugin:8.8.2")
    }
}

apply(plugin = "javafx-gradle-plugin")
repositories {
    mavenCentral()
    jcenter()
}

dependencies {  
    implementation(kotlin("stdlib"))
    implementation(project(":my-game-common", "jvmRuntimeElements"))
    implementation(project(":text-adventure-engine-common","jvmRuntimeElements"))
    compile(project(":my-game-common", "jvmRuntimeElements"))
    compile("io.github.microutils:kotlin-logging:1.6.23")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    listOf("slf4j-api", "slf4j-log4j12").forEach {
        implementation(group = "org.slf4j", name = it, version = "1.7.25")
    }
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

idea {
    module{
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel("1.8")
    }
}


tasks.findByPath("jacocoTestReport")?.dependsOn("test")
tasks.findByPath("build")?.dependsOn("jacocoTestReport")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

apply {
    from("javafx.gradle")
}