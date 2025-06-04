package io.github.alexsarrell.arch.gradle.api.io

import io.github.alexsarrell.arch.core.model.SupportedFileType
import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File

object ConfigurationFileLoader : FileLoader {
    private val configurationPattern = "^[a-zA-Z0-9_.-]+:[a-zA-Z0-9_.-]+(?::/.*)?$".toRegex()

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
        val dependencies =
            project.configurations
                .filter { it.isCanBeResolved }
                .flatMap { it.resolve() }

        val specJar =
            dependencies.find {
                    configuration.pattern.containsMatchIn(it.path)
                } ?: throw GradleException("Dependency $basePath is not found")

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
        val pattern: Regex,
        val filePath: String = "",
    ) {
        constructor(uri: String) : this(
            uri.split(":/").first()
                .replace(":", "[\\\\/]+")
                .toRegex(),
            uri.split(":/").getOrElse(1) { "" },
        )
    }
}
