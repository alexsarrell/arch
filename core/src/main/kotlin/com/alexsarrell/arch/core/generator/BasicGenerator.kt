package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.io.FileCodeWriter
import com.alexsarrell.arch.core.model.accessor.ModelAccessor
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.generatorFileExtension
import com.alexsarrell.arch.core.pipeline.context.outputDir
import com.alexsarrell.arch.core.pipeline.context.packageName
import com.alexsarrell.arch.core.pipeline.context.sourceDir
import com.alexsarrell.arch.core.pipeline.context.templateDir
import com.alexsarrell.arch.core.pipeline.pipe.context.ClassSchema
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.specs
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.FileTemplateLoader

/**
 * protected handlebars
 * ctor resource prefix
 *
 */
class BasicGenerator : AbstractGenerator("/templates") {

}

abstract class AbstractGenerator(templatesPrefix: String) : CodeGenerator {
    private val handlebars =
        Handlebars()
            .apply {
                with(ClassPathTemplateLoader(templatesPrefix, ".hbs"))
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
            buildPath("${modelAccessor.className}.${context.generatorFileExtension}", context),
        ).writeResult(
            compiled.apply(modelAccessor),
            context,
        )
    }

    private fun buildPath(
        fileName: String,
        context: PipelineContext,
    ): String =
        context.outputDir.split("/")
            .plus(context.sourceDir.split("/"))
            .plus(context.packageName.split("."))
            .plus(listOf(fileName))
            .joinToString("/")
}
