import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask

plugins {
    `kotlin-configuration`
    id("com.alexsarrell.cor4al.generator.kotlin.plugin") version "0.1.0"
}

dependencies {
    api(project(":core"))
    api(project(":generator:gradle-kotlin-plugin"))
}

tasks.register<Cor4alGenerateTask>("cor4alGenerate") {
    doNotTrackState("If you want to always regenerate model classes")
    specSource.set(project.file(".").absolutePath)
    outputDir.set("${project.layout.buildDirectory.get()}/generated")
    packageName.set("com.alexsarrell.demo")
}

tasks.named("compileKotlin") {
    dependsOn("cor4alGenerate")
}

sourceSets {
    main {
        java {
            srcDirs("${project.layout.buildDirectory.get()}/generated/src/main/kotlin")
        }
    }
}