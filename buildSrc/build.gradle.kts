plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
}

val kotlinVersion = "1.9.22"

dependencies {
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    implementation("org.jetbrains.kotlin.plugin.noarg:org.jetbrains.kotlin.plugin.noarg.gradle.plugin:$kotlinVersion")
    implementation(gradleApi())
}