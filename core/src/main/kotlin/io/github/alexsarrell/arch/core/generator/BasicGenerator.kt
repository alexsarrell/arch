package io.github.alexsarrell.arch.core.generator

import com.github.jknack.handlebars.io.FileTemplateLoader
import io.github.alexsarrell.arch.core.generator.helper.IsEmptyHelper
import io.github.alexsarrell.arch.core.model.Spec
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.model.accessor.ObjectTemplateData
import io.github.alexsarrell.arch.core.pipeline.context.generatorFileExtension
import io.github.alexsarrell.arch.core.pipeline.context.sourceDir
import io.github.alexsarrell.arch.core.pipeline.pipe.context.ClassSchema
import io.github.alexsarrell.arch.core.util.build
import io.github.alexsarrell.arch.core.util.writeToFile
import java.io.File

class BasicGenerator(
    context: TaskContext,
) : AbstractGenerator(
        "/templates",
        context,
    ) {
    init {
        if (context.templateDir != null) {
            handlebars.with(FileTemplateLoader(context.templateDir))
        }
        handlebars.registerHelper("isEmpty", IsEmptyHelper())
    }

    override fun generateSpec(spec: Spec) {
        spec.schemas.onEach(::generateSchema)
    }

    private fun generateSchema(schema: ClassSchema) {
        val template = handlebars.compile(schema.value.type.name.lowercase())
        val objectTemplateData = ObjectTemplateData(schema.key, schema.value, context)
        val file = File(buildPath(objectTemplateData)).build()

        template.writeToFile(objectTemplateData, file)
    }

    private fun buildPath(objectTemplateData: ObjectTemplateData): String =
        context.run {
            outputDir
                .split("/", "\\")
                .plus(sourceDir.split("/", "\\"))
                .plus(packageName.split("."))
                .plus("${objectTemplateData.className}.$generatorFileExtension")
                .joinToString(File.separator)
        }
}
