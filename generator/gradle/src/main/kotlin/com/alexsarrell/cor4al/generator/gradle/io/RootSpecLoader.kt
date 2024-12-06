package com.alexsarrell.cor4al.generator.gradle.io

import org.gradle.api.Project
import java.io.File

object RootSpecLoader : SpecLoader {

    override fun get(specDir: String, project: Project): Set<File> {
        val sourceDir = File(specDir)

        require(sourceDir.exists()) {
            "Source directory doesn't exist: ${sourceDir.absolutePath}"
        }
        require(sourceDir.isDirectory) {
            "Source path is not a directory: ${sourceDir.absolutePath}"
        }

        return sourceDir.walk()
            .filter { it.isFile }
            .filter { it.name.matches(".*\\.(yaml|yml)".toRegex()) }
            .toSet()
    }
}
