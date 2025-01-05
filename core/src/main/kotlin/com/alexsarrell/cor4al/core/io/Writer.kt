package com.alexsarrell.cor4al.core.io

import com.alexsarrell.cor4al.core.pipeline.context.GenerationContext

interface Writer {
    fun writeResult(
        code: String,
        context: GenerationContext,
    )
}