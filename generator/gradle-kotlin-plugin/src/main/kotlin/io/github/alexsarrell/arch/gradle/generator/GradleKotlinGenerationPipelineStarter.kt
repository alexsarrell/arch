package io.github.alexsarrell.arch.gradle.generator

import io.github.alexsarrell.arch.core.generator.BasicGenerator
import io.github.alexsarrell.arch.core.generator.DocumentationGenerator
import io.github.alexsarrell.arch.core.parser.BasicYamlParser
import io.github.alexsarrell.arch.core.pipeline.*
import io.github.alexsarrell.arch.core.pipeline.context.*
import io.github.alexsarrell.arch.core.pipeline.pipe.impl.GeneratePipe
import io.github.alexsarrell.arch.core.pipeline.pipe.impl.ParsePipe
import io.github.alexsarrell.arch.gradle.api.GradleGenerationPipelineStarter
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.helper.GenerationFlowDefinition
import io.github.alexsarrell.arch.gradle.api.pipeline.pipe.LoadPipe

class GradleKotlinGenerationPipelineStarter : GradleGenerationPipelineStarter {
    private val context: PipelineContext = BasicPipelineContext()
    private val pipeline: GenerationPipeline = BasicGenerationPipeline(GenerationFlowDefinition())
    private val parser: BasicYamlParser = BasicYamlParser()

    override fun runPipeline(task: TaskContext) {
        fillContextStartParams(task)
        val codeGenerator = BasicGenerator(task)
        val docsGenerator = DocumentationGenerator(context)

        pipeline.generate {
            process(LoadPipe(context))
            process(ParsePipe(parser))
            if (context.generateModel) process(GeneratePipe(codeGenerator))
            if (context.generateModelDocs) process(GeneratePipe(docsGenerator))
        }
    }

    private fun fillContextStartParams(task: TaskContext) {
        task.generatorFileExtension = "kt"
        task.sourceDir = "src/main/kotlin"
    }
}
