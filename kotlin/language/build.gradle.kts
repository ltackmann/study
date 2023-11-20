group = "com.example"
version = "0.0.1"

plugins {
    `java-library`
    kotlin("jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.apache.commons:commons-math3:3.6.1")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}

java {
    toolchain{
        // kotlin does not work with later versions
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}