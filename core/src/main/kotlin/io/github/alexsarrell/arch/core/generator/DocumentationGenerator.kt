package io.github.alexsarrell.arch.core.generator

import io.github.alexsarrell.arch.core.generator.helper.JsonFormatHelper
import io.github.alexsarrell.arch.core.model.DocFormat
import io.github.alexsarrell.arch.core.model.Spec
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.util.build
import io.github.alexsarrell.arch.core.util.writeToFile
import java.io.File

class DocumentationGenerator(
    context: TaskContext,
) : AbstractGenerator(
        "/templates/docs/${context.modelDocsLocale.dir}",
        context,
    ) {
    init {
        handlebars.registerHelper("jsonFormatted", JsonFormatHelper())
    }

    override fun generateSpec(spec: Spec) {
        context.modelDocsFormat.let { format ->
            val template = handlebars.compile(format.templateName)
            val file = File(buildPath(spec, format)).build()

            template.writeToFile(spec, file)
        }
    }

    private fun buildPath(
        spec: Spec,
        format: DocFormat,
    ): String =
        context.modelDocsOutputDir
            .split("/", "\\")
            .plus("${spec.getFileName()}.${format.extension}")
            .joinToString(File.separator)
}
