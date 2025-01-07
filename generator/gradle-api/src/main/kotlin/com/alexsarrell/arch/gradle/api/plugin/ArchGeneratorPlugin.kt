package com.alexsarrell.arch.gradle.api.plugin

import com.alexsarrell.arch.gradle.api.ArchExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ArchGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("arch", ArchExtension::class.java)
    }
}
