package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext

interface CodeGenerator {

    fun PipelineContext.generate(context: ParsePipeContext)
}
