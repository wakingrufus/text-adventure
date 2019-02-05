//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//
//plugins {
//    idea
//    kotlin("jvm") version "1.3.11"
//    jacoco
//    application
//}
//
//application {
//    mainClassName = "com.github.wakingrufus.adventure.MainKt"
//}
//
//repositories {
//    mavenCentral()
//    jcenter()
//}
//
//dependencies {
//    implementation(project(":text-adventure-jvm"))
//    implementation(kotlin("stdlib"))
//    implementation("io.github.microutils:kotlin-logging:1.6.10")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    listOf("slf4j-api", "slf4j-log4j12").forEach {
//        implementation(group = "org.slf4j", name = it, version = "1.7.25")
//    }
//    testImplementation("org.jetbrains.kotlin:kotlin-test:1.3.11")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
//
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
////    expectedBy(project(":text-adventure-engine-common"))
//}
//
//idea {
//    module{
//        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel("1.8")
//    }
//}
//
//
//tasks.findByPath("jacocoTestReport")?.dependsOn("test")
//tasks.findByPath("build")?.dependsOn("jacocoTestReport")
//
//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        jvmTarget = "1.8"
//        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
//    }
//}
//
//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}
//
//apply {
//    from("javafx.gradle")
//}