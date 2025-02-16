import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "1.3.1"
}

group = "com.alexsarrell.arch"
version = "1.0.0"

dependencies {
    api(project(":core"))
    api(project(":generator:gradle-api"))
    api(project(":generator:kotlin"))
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleKotlinDsl())
    implementation(gradleApi())
}

gradlePlugin {
    website = "https://github.com/alexsarrell/arch"
    vcsUrl = "https://github.com/alexsarrell/arch.git"

    plugins {
        create("archKotlinPlugin") {
            id = "com.alexsarrell.arch.generator.kotlin.plugin"
            displayName = "ARCH Kotlin Gradle Plugin"
            implementationClass = "com.alexsarrell.arch.gradle.plugin.KotlinPluginModule"
            description =
                "A Gradle plugin for ARCH library. Use it to generate Kotlin classes and docs for analytics using ARCH specification. See examples of usage at https://github.com/alexsarrell/arch/tree/master/demo"

            website = "https://github.com/alexsarrell/arch"
            vcsUrl = "https://github.com/alexsarrell/arch.git"
            tags = listOf("arch", "codegen", "kotlin")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("gradleKotlinPluginPublication") {
            from(components["java"])
            artifactId = "gradle-kotlin-plugin"
        }
    }
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

tasks.named("publishToMavenLocal") {
    dependsOn(":core:publishToMavenLocal")
    dependsOn(":generator:gradle-api:publishToMavenLocal")
    dependsOn(":generator:kotlin:publishToMavenLocal")
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    languageVersion = "1.9"
}
