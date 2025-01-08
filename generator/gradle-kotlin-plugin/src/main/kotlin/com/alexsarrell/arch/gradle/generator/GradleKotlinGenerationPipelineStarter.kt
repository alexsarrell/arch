package com.alexsarrell.arch.gradle.generator

import com.alexsarrell.arch.core.pipeline.pipe.impl.GeneratePipe
import com.alexsarrell.arch.gradle.api.GradleGenerationPipelineStarter
import com.alexsarrell.arch.core.pipeline.pipe.impl.ParsePipe
import com.alexsarrell.arch.core.generator.BasicGenerator
import com.alexsarrell.arch.core.generator.DocumentationGenerator
import com.alexsarrell.arch.core.model.DocType
import com.alexsarrell.arch.core.parser.BasicYamlParser
import com.alexsarrell.arch.core.pipeline.*
import com.alexsarrell.arch.core.pipeline.context.*
import com.alexsarrell.arch.generator.kotlin.JavaImportMappings
import com.alexsarrell.arch.gradle.api.pipeline.pipe.LoadPipe
import com.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask

class GradleKotlinGenerationPipelineStarter : GradleGenerationPipelineStarter {
    private val pipeline: GenerationPipeline = BasicGenerationPipeline()
    private val context: PipelineContext = pipeline.context
    private val parser: BasicYamlParser = BasicYamlParser()
    private val docsGenerator: DocumentationGenerator = DocumentationGenerator()

    override fun runPipeline(task: ArchGenerateTask) {
        fillContextStartParams(task)
        val codeGenerator = BasicGenerator(context)

        pipeline.generate {
            process(LoadPipe(task))
            process(ParsePipe(parser))
            if (context.generateModel) process(GeneratePipe(codeGenerator))
            if (context.generateModelDocs) process(GeneratePipe(docsGenerator))
        }
    }

    private fun fillContextStartParams(task: ArchGenerateTask) {
        context.packageName = task.packageName.get()
        context.outputDir = task.outputDir.get()
        context.templateDir = task.templateDir.orNull
        context.generatorFileExtension = "kt"
        context.importMappings = JavaImportMappings.getMappings() + task.importMappings.getOrElse(mapOf())
        context.specLimit = task.specsLimit.get()
        context.specSource = task.specSource.get()
        context.generateModel = task.generateModel.getOrElse(true)
        context.metadataAccessors = task.metadataAccessors.getOrElse(true)
        context.generateModelDocs = task.generateModelDocs.getOrElse(false)
        context.modelDocsOutputDir = task.modelDocsOutputDir.getOrElse("${task.project.projectDir}/docs")
        context.modelDocType = DocType.MARKDOWN
        context.sourceDir = "src/main/kotlin"
    }
}
