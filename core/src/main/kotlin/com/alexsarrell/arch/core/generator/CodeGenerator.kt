package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext

interface CodeGenerator {

    fun generate(context: ParsePipeContext)
}
