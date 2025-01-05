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
    group = "com.alexsarrell.cor4al"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}