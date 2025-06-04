import io.github.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask

plugins {
    `kotlin-configuration`
    id("io.github.alexsarrell.arch-kotlin-plugin") version "1.0.0"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":generator:gradle-kotlin-plugin"))
}

tasks.register<ArchGenerateTask>("archGenerate") {
    specSource.set("${project.projectDir}")
    specsLimit.set(listOf("core-demo.yml"))
    outputDir.set("${project.layout.buildDirectory.get()}/generated")
    generateModelDocs.set(true)
    modelDocsFormat.set(listOf("markdown"))
    packageName.set("com.alexsarrell.demo")
}

tasks.named("compileKotlin") {
    dependsOn("archGenerate")
}

sourceSets {
    main {
        java {
            srcDirs("${project.layout.buildDirectory.get()}/generated/src/main/kotlin")
        }
    }
}
