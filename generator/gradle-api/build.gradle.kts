plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.alexsarrell.arch"
version = "1.0.3"

dependencies {
    api(project(":core"))
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("archPlugin") {
            id = "com.alexsarrell.arch.generator"
            implementationClass = "com.alexsarrell.arch.gradle.api.plugin.ArchGeneratorPlugin"
        }
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("gradleApiPublication") {
            artifactId = "gradle-api"
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}

tasks.named("publishToMavenLocal") {
    dependsOn(":core:publishToMavenLocal")
}
