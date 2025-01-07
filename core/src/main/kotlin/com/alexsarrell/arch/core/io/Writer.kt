package com.alexsarrell.arch.core.io

import com.alexsarrell.arch.core.pipeline.context.PipelineContext

interface Writer {
    fun writeResult(
        code: String,
        context: PipelineContext,
    )
}