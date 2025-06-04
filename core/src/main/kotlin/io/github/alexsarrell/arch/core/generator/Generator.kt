package io.github.alexsarrell.arch.core.generator

import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext

interface Generator {
    fun generate(context: ParsePipeContext)
}
