package com.alexsarrell.cor4al.generator.gradle.io

import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File

class ConfigurationSpecLoader(
    private val configuration: String,
) : SpecLoader {

    /**
     * @param specDir relative file path from the root of the jar file
     */
    override fun get(specDir: String, project: Project): Set<File> {
        val specJar = project.configurations
            .filter { it.isCanBeResolved }
            .flatMap { it.resolve() }
            .find { it.name.contains(configuration) }
            ?: throw GradleException("Configuration $configuration not found in dependencies")

        return project.zipTree(specJar).matching { it.include("$specDir/**/*.yml", "$specDir/**/*.yaml") }.files
    }
}
