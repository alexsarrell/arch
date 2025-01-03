package com.alexsarrell.cor4al.gradle.api.io

import com.alexsarrell.cor4al.gradle.api.model.LoadResult
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.LoadPipeContext
import org.gradle.api.Project
import java.io.File

interface FileLoader {

    fun canLoad(filePath: String): Boolean

    fun get(basePath: String, project: Project): LoadResult
}
