plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "io.github.alexsarrell"
version = "1.0.3"

dependencies {
    api(project(":core"))
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleApi())
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.named("publishToMavenLocal") {
    dependsOn(":core:publishToMavenLocal")
}
