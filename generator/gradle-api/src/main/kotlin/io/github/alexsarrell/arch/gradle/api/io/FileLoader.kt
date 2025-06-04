package io.github.alexsarrell.arch.gradle.api.io

import java.io.File

interface FileLoader {

    fun canLoad(filePath: String): Boolean

    fun get(basePath: String): Set<File>
}
