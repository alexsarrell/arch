package com.alexsarrell.arch.core.io

import com.alexsarrell.arch.core.configuration.FileWriterConfiguration
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.outputDir
import com.alexsarrell.arch.core.pipeline.context.packageName
import java.io.File

class FileCodeWriter(
    private val configuration: FileWriterConfiguration,
    private val fileName: String,
) : Writer {
    override fun writeResult(
        code: String,
        context: PipelineContext,
    ) {
        File(buildPath(fileName, context)).apply { parentFile?.mkdirs() }.writeText(code)
    }

    private fun buildPath(
        fileName: String,
        context: PipelineContext,
    ): String = context.outputDir + configuration.sourceDir + "/" + context.packageName.split(".").joinToString("/") + "/$fileName"
}
