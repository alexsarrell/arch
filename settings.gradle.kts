rootProject.name = "cor4al"

include(":core")
include(":generator:gradle-api")
include(":generator:gradle-kotlin-plugin")
include(":generator:kotlin")
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