package io.github.alexsarrell.arch.gradle.api.plugin

import io.github.alexsarrell.arch.gradle.api.ArchExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ArchGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("arch", ArchExtension::class.java)
    }
}
