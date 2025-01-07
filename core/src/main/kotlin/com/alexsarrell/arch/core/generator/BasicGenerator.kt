package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.configuration.FileWriterConfiguration
import com.alexsarrell.arch.core.io.FileCodeWriter
import com.alexsarrell.arch.core.model.accessor.ModelAccessor
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.generatorFileExtension
import com.alexsarrell.arch.core.pipeline.pipe.context.ClassSchema
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.context.templateDir
import com.alexsarrell.arch.core.pipeline.pipe.context.specs
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.FileTemplateLoader

class BasicGenerator : CodeGenerator {
    private val writerConfiguration = FileWriterConfiguration("/src/main/kotlin")

    private val handlebars =
        Handlebars()
            .apply {
                with(ClassPathTemplateLoader("/templates", ".hbs"))
            }

    override fun generate(context: ParsePipeContext) {
        if (context.pipelineContext.templateDir != null) {
            handlebars.with(FileTemplateLoader(context.pipelineContext.templateDir))
        }

        context.specs.forEach { spec ->
            spec.schemas.forEach {
                generateSchema(it.key to it.value, context.pipelineContext)
            }
        }
    }

    private fun generateSchema(
        schema: ClassSchema,
        context: PipelineContext,
    ) {
        val compiled =
            handlebars.compile(
                schema.second.type.name
                    .lowercase(),
            )

        val modelAccessor =
            ModelAccessor(schema, context)

        FileCodeWriter(
            writerConfiguration,
            "${modelAccessor.className}.${context.generatorFileExtension}",
        ).writeResult(
            compiled.apply(modelAccessor),
            context,
        )
    }
}
