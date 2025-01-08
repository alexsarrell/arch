package com.alexsarrell.arch.core.generator

import com.alexsarrell.arch.core.generator.helper.JsonFormatHelper
import com.alexsarrell.arch.core.model.Spec
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.modelDocType
import com.alexsarrell.arch.core.pipeline.context.modelDocsOutputDir
import com.alexsarrell.arch.core.util.build
import com.alexsarrell.arch.core.util.writeToFile
import java.io.File

class DocumentationGenerator : AbstractGenerator("/templates/docs") {
    init {
        handlebars.registerHelper("jsonFormatted", JsonFormatHelper())
    }

    override fun PipelineContext.generateSpec(spec: Spec) {
        val template = handlebars.compile(modelDocType.templateName)
        val file = File(buildPath(spec)).build()

        template.writeToFile(spec, file)
    }

    private fun PipelineContext.buildPath(spec: Spec): String {
        return modelDocsOutputDir.split("/", "\\")
            .plus("${spec.getFileName()}.${modelDocType.extension}")
            .joinToString(File.separator)
    }
}
