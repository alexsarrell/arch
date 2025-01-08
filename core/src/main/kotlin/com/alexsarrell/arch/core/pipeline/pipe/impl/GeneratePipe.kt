package com.alexsarrell.arch.core.pipeline.pipe.impl

import com.alexsarrell.arch.core.generator.CodeGenerator
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.pipe.ChildPipe
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext

class GeneratePipe(
    private val codeGenerator: CodeGenerator,
) : ChildPipe<ParsePipeContext>(ParsePipeContext::class.java) {
    override fun PipelineContext.process(parentContext: ParsePipeContext) {
        codeGenerator.run { generate(parentContext) }
    }
}