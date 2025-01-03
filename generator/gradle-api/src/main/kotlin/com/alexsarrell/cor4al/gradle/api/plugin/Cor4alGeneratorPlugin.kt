package com.alexsarrell.cor4al.gradle.api.plugin

import com.alexsarrell.cor4al.gradle.api.Cor4alExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Cor4alGeneratorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("cor4al", Cor4alExtension::class.java)
    }
}
