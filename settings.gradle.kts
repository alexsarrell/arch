rootProject.name = "arch"

include(
    ":core",
    ":generator:gradle-api",
    ":generator:kotlin",
    ":generator:gradle-kotlin-plugin",
    ":demo"
)
findProject("generator:kotlin")?.name = "generator-kotlin"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}