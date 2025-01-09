package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.generator.helper.JsonFormatHelper
import com.alexsarrell.arch.core.model.DocFormat
import com.alexsarrell.arch.core.model.DocLocale
import com.alexsarrell.arch.core.model.Spec
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.modelDocsFormat
import com.alexsarrell.arch.core.pipeline.context.modelDocsOutputDir
import com.alexsarrell.arch.core.util.build
import com.alexsarrell.arch.core.util.writeToFile
import java.io.File

class DocumentationGenerator(locale: DocLocale) : AbstractGenerator("/templates/docs/${locale.dir}") {
    init {
        handlebars.registerHelper("jsonFormatted", JsonFormatHelper())
    }

    override fun PipelineContext.generateSpec(spec: Spec) {
        modelDocsFormat.forEach { format ->
            val template = handlebars.compile(format.templateName)
            val file = File(buildPath(spec, format)).build()

            template.writeToFile(spec, file)
        }
    }

    private fun PipelineContext.buildPath(spec: Spec, format: DocFormat): String {
        return modelDocsOutputDir.split("/", "\\")
            .plus("${spec.getFileName()}.${format.extension}")
            .joinToString(File.separator)
    }
}
