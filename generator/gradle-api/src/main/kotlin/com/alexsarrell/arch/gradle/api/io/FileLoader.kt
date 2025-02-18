package com.alexsarrell.arch.gradle.api.io

import org.gradle.api.Project
import java.io.File

interface FileLoader {

    fun canLoad(filePath: String): Boolean

    fun get(basePath: String, project: Project): Set<File>
}
