plugins {
    idea
}

repositories {
    mavenCentral()
    jcenter()
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = "5.2"
    distributionType = Wrapper.DistributionType.ALL
}

allprojects{
    version = "0.2.0"
    group = "com.github.wakingrufus"
}