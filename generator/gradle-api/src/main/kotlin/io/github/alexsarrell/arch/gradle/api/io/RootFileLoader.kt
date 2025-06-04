package io.github.alexsarrell.arch.gradle.api.io

import io.github.alexsarrell.arch.core.model.SupportedFileType
import org.gradle.api.Project
import java.io.File

object RootFileLoader : FileLoader {
    override fun canLoad(filePath: String): Boolean {
        val sourceDir = File(filePath)
        return sourceDir.exists() && sourceDir.isDirectory
    }

    override fun get(basePath: String, project: Project): Set<File> {
        val sourceDir = File(basePath)

        require(sourceDir.exists()) {
            "Source directory doesn't exist: ${sourceDir.absolutePath}"
        }
        require(sourceDir.isDirectory) {
            "Source path is not a directory: ${sourceDir.absolutePath}"
        }

        val specs =
            sourceDir.walk()
                .filter { it.isFile }
                .filter {
                    it.name.matches(SupportedFileType.ALL.pattern)
                }
                .toSet()

        return specs
    }
}
