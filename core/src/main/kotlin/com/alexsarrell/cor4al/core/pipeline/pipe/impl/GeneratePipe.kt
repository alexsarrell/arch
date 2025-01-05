package com.alexsarrell.cor4al.core.pipeline.pipe.impl

import com.alexsarrell.cor4al.core.generator.CodeGenerator
import com.alexsarrell.cor4al.core.pipeline.pipe.ChildPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext

class GeneratePipe(
    private val codeGenerator: CodeGenerator,
) : ChildPipe<ParsePipeContext>(ParsePipeContext::class.java) {
    override fun process(parentContext: ParsePipeContext) {
        codeGenerator.generate(parentContext)
    }
}