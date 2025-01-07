import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    id("com.alexsarrell.cor4al.generator") version "1.0.0"
}

group = "com.alexsarrell.cor4al"
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
    plugins {
        create("cor4alKotlinPlugin") {
            id = "com.alexsarrell.cor4al.generator.kotlin.plugin"
            implementationClass = "com.alexsarrell.cor4al.gradle.plugin.KotlinPluginModule"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "generator-gradle-kotlin-plugin"
            from(components["java"])
        }
    }
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    languageVersion = "1.9"
}