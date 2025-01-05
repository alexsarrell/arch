package com.alexsarrell.cor4al.gradle.api.io

import com.alexsarrell.cor4al.core.model.SupportedFileType
import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File

object ConfigurationFileLoader : FileLoader {
    private val configurationPattern = ".*\\..*\\.*:/.*".toRegex()

    override fun canLoad(filePath: String): Boolean = configurationPattern.matches(filePath)

    /**
     * @param basePath configuration name with (or without) file path from the root of the jar file
     *
     * Example: ```com.alexsarrell.demo:external-configuration:/data/schemas```
     */
    override fun get(
        basePath: String,
        project: Project,
    ): Set<File> {
        val configuration = Configuration(basePath)
        val specJar =
            project.configurations
                .filter { it.isCanBeResolved }
                .flatMap { it.resolve() }
                .find { it.name.contains(configuration.name) }
                ?: throw GradleException("Configuration $basePath not found in dependencies")

        val specs =
            project
                .zipTree(specJar)
                .matching {
                    include("${configuration.filePath}/**")
                }.filter {
                    SupportedFileType.ALL.pattern.matches(it.name)
                }.files

        return specs
    }

    private class Configuration(
        val name: String,
        val filePath: String = "",
    ) {
        constructor(uri: String) : this(
            uri.split(":/").first(),
            uri.split(":/").getOrElse(1) { "" },
        )
    }
}
