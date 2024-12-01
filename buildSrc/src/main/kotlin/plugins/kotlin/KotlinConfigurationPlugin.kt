package plugins.kotlin

import Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinConfigurationPlugin : Plugin<Project> {

    override fun apply(target: Project) = apply(target) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")

        dependencies {
            "implementation"(kotlin("stdlib", Versions.kotlinVersion))
            "implementation"(kotlin("stdlib-common", Versions.kotlinVersion))
            "implementation"(kotlin("reflect", Versions.kotlinVersion))
            "implementation"(kotlin("gradle-plugin", Versions.kotlinVersion))
            "implementation"(kotlin("noarg", Versions.kotlinVersion))
        }

        tasks.withType<JavaCompile> {
            sourceCompatibility = Versions.javaVersion
            targetCompatibility = Versions.javaVersion
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = Versions.javaVersion
            kotlinOptions.languageVersion = Versions.kotlinLangVersion
            kotlinOptions.apiVersion = Versions.kotlinLangVersion
        }
    }
}

inline fun Plugin<*>.apply(project: Project, builder: Project.() -> Unit) { builder(project) }