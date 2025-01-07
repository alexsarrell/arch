rootProject.name = "arch"

include(
    ":core",
    ":generator:gradle-api",
    ":generator:kotlin",
    ":generator:gradle-kotlin-plugin",
)
findProject("generator:kotlin")?.name = "generator-kotlin"
include(":demo")
findProject(":demo")?.name = "demo"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}