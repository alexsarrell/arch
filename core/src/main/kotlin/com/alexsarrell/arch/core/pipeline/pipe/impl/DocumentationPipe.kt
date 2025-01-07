package com.alexsarrell.arch.core.pipeline.pipe.impl

import com.alexsarrell.arch.core.model.Spec
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.context.modelDocType
import com.alexsarrell.arch.core.pipeline.context.modelDocsOutputDir
import com.alexsarrell.arch.core.pipeline.pipe.ChildPipe
import com.alexsarrell.arch.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.specs
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import java.io.File

class DocumentationPipe(
    private val context: PipelineContext,
) : ChildPipe<ParsePipeContext>(ParsePipeContext::class.java) {

    private val handlebars = Handlebars()
        .apply {
            with(ClassPathTemplateLoader("/templates/docs", ".hbs"))
        }

    override fun process(parentContext: ParsePipeContext) {
        val template =
            handlebars.compile(context.modelDocType)

        parentContext.specs.forEach {
            it.generateDoc(template)
        }
    }

    private fun Spec.generateDoc(template: Template): String {
        TODO("Spec accessor")
    }

    private fun String.writeDoc(documentName: String) {
        File(context.modelDocsOutputDir + "/$documentName").writeText(this)
    }
}