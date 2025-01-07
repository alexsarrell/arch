plugins {
    `kotlin-configuration`
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.alexsarrell.cor4al"
version = "1.0.0"

dependencies {
    api(project(":core"))
    // implementation("org.slf4j:slf4j-api", Versions.loggingVersion)
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("cor4alPlugin") {
            id = "com.alexsarrell.cor4al.generator"
            implementationClass = "com.alexsarrell.cor4al.gradle.api.plugin.Cor4alGeneratorPlugin"
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
