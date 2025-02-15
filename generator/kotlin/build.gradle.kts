plugins {
    `kotlin-configuration`
    `maven-publish`
}

dependencies {
    api(project(":generator:gradle-api"))
    api(project(":core"))
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleApi())

    implementation("com.github.jknack:handlebars:4.3.1")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-script-util:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-compiler:1.8.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.mockk:mockk:1.13.8")
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("kotlinPublication") {
            artifactId = "kotlin"
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}

tasks.named("publishToMavenLocal") {
    dependsOn(":core:publishToMavenLocal")
}

tasks.test {
    useJUnitPlatform()
}
