package com.alexsarrell.cor4al.gradle.api.io

import com.alexsarrell.cor4al.core.model.SupportedFileType
import com.alexsarrell.cor4al.gradle.api.model.LoadResult
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.cor4al.core.util.relativeToBaseDir
import com.alexsarrell.cor4al.core.util.relativeToBaseFile
import org.gradle.api.Project
import java.io.File
import java.net.URI

object RootFileLoader : FileLoader {
    override fun canLoad(filePath: String): Boolean {
        // TODO make File from path and check it is directory
        return true
    }

    override fun get(basePath: String, project: Project): LoadResult {
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

        return LoadResult(sourceDir, specs)
    }
}
