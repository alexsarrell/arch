// Use for test purposes
tasks.register("publishAllToMavenLocal") {
    group = "test publishing"
    listOf(
        ":core",
        ":generator:gradle-api",
        ":generator:gradle-kotlin-plugin",
        ":generator:kotlin",
    ).forEach {
        dependsOn("$it:clean")
        dependsOn("$it:publishToMavenLocal")
    }
}

allprojects {
    group = "io.github.alexsarrell"
    version = "1.0.0"

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}