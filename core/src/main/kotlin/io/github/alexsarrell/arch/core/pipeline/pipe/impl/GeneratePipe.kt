package io.github.alexsarrell.arch.core.pipeline.pipe.impl

import io.github.alexsarrell.arch.core.generator.Generator
import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext
import io.github.alexsarrell.arch.core.pipeline.pipe.ChildPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext

class GeneratePipe(
    private val generator: Generator,
) : ChildPipe<ParsePipeContext>(ParsePipeContext::class.java) {
    override fun PipelineContext.process(parentContext: ParsePipeContext) {
        generator.run { generate(parentContext) }
    }
}