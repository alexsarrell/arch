package io.github.alexsarrell.arch.core.pipeline.pipe.impl

import io.github.alexsarrell.arch.core.parser.YamlParser
import io.github.alexsarrell.arch.core.pipeline.pipe.AdjacentPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.specFiles
import io.github.alexsarrell.arch.core.pipeline.pipe.context.typeMappingContent

class ParsePipe(
    private val parser: YamlParser,
) : AdjacentPipe<LoadPipeContext, ParsePipeContext>() {

    override fun process(context: LoadPipeContext): ParsePipeContext {
        val pipeContext = ParsePipeContext(context.taskContext)
        parser.parseSpecs(
            context.specFiles,
            context.taskContext.specsLimit,
            pipeContext,
        )
        context.typeMappingContent?.let {
            parser.parseMappings(it, pipeContext)
        }
        return pipeContext
    }
}
