import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.ryanpmartz.kaoc"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_22
}

repositories {
    mavenCentral()
}

val jUnitVersionNo = "5.9.3"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersionNo")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersionNo")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "22"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
