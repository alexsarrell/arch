package io.github.alexsarrell.arch.core.pipeline.pipe.impl

import io.github.alexsarrell.arch.core.generator.Generator
import io.github.alexsarrell.arch.core.pipeline.pipe.FinalPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext

class GeneratePipe(
    private val generator: Generator,
) : FinalPipe<ParsePipeContext>() {
    override fun process(context: ParsePipeContext) {
        generator.run { generate(context) }
    }
}
