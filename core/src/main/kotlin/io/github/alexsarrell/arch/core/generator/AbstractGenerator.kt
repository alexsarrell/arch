package io.github.alexsarrell.arch.core.generator

import io.github.alexsarrell.arch.core.model.Spec
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.specs
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import io.github.alexsarrell.arch.core.model.TaskContext

abstract class AbstractGenerator(
    templatesPrefix: String,
    protected val context: TaskContext,
) : Generator {
    protected val handlebars =
        Handlebars()
            .apply {
                with(ClassPathTemplateLoader(templatesPrefix, ".hbs"))
            }

    protected abstract fun generateSpec(spec: Spec)

    override fun generate(context: ParsePipeContext) {
        context.specs.onEach(::generateSpec)
    }
}
