package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.generator.CodeGenerator
import com.alexsarrell.cor4al.core.pipeline.context.GenerationContext
import com.alexsarrell.cor4al.core.pipeline.pipe.ChildPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.GeneratePipeContext

class GeneratePipe(
    context: GenerationContext,
    private val codeGenerator: CodeGenerator,
) : ChildPipe<ParsePipeContext>(ParsePipeContext::class.java) {
    override val pipeContext: PipeContext = GeneratePipeContext(context)

    override fun process(parentContext: ParsePipeContext) {
        codeGenerator.generate(parentContext)
    }
}