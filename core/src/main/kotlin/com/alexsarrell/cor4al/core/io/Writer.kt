package com.alexsarrell.cor4al.core.io

import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext

interface Writer {
    fun writeResult(
        code: String,
        context: PipelineContext,
    )
}