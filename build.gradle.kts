import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
}

dependencies {
    implementation("com.beust:klaxon:5.5")
    testImplementation("org.reflections:reflections:0.10.2")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
    testImplementation("com.github.ben-manes.caffeine:caffeine:2.9.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.21")
}

group = "me.xiacongling"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
}