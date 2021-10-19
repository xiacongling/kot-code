import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

dependencies {
    implementation("com.beust:klaxon:5.5")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")
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