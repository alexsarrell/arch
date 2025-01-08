package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.model.Spec
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.specs
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader

abstract class AbstractGenerator(templatesPrefix: String) : CodeGenerator {
    protected val handlebars =
        Handlebars()
            .apply {
                with(ClassPathTemplateLoader(templatesPrefix, ".hbs"))
            }

    protected abstract fun PipelineContext.generateSpec(
        spec: Spec,
    )

    override fun PipelineContext.generate(context: ParsePipeContext) {
        context.specs.forEach { generateSpec(it) }
    }
}
