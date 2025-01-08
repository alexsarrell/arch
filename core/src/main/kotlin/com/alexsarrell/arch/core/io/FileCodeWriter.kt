package com.alexsarrell.arch.core.io

import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import java.io.File

class FileCodeWriter(
    private val filePath: String,
) : Writer {
    override fun writeResult(
        code: String,
        context: PipelineContext,
    ) {
        File(filePath).apply { parentFile?.mkdirs() }.writeText(code)
    }
}

