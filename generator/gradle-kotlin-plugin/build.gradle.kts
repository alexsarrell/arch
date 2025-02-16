import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    id("java-gradle-plugin")
}

group = "com.alexsarrell.arch"

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
        create("archKotlinPlugin") {
            id = "com.alexsarrell.arch.generator.kotlin.plugin"
            implementationClass = "com.alexsarrell.arch.gradle.plugin.KotlinPluginModule"
        }
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("gradleKotlinPluginPublication") {
            from(components["java"])
            artifactId = "gradle-kotlin-plugin"
            artifact(tasks["sourcesJar"])
        }
    }
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
        uri("https://jitpack.io")
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
