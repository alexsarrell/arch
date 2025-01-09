plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.alexsarrell.arch"
version = "1.0.0"

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

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "gradle-api"
            from(components["java"])
        }
    }
}

tasks.named("publishToMavenLocal") {
    dependsOn(":core:publishToMavenLocal")
}
