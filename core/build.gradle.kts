plugins {
    `kotlin-configuration`
    `maven-publish`
    id("org.jetbrains.kotlin.plugin.serialization") version Versions.kotlinVersion
}

dependencies {
    implementation("com.charleskorn.kaml:kaml:0.55.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jacksonVersion}")
    implementation("com.github.jknack:handlebars:${Versions.handlebarsVersion}")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-script-util:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-compiler:1.8.0")
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "core"
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}
