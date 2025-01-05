package com.alexsarrell.cor4al.core.generator

import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext

interface CodeGenerator {

    fun generate(context: ParsePipeContext)
}
