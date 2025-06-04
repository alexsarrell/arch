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
import io.github.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import io.github.alexsarrell.arch.gradle.api.io.ConfigurationFileLoader
import io.github.alexsarrell.arch.gradle.api.io.FileLoaderAccessor
import io.github.alexsarrell.arch.gradle.api.io.RootFileLoader
import io.github.alexsarrell.arch.gradle.api.pipeline.pipe.LoadPipe
import org.gradle.api.Project

class GradleKotlinGenerationPipelineStarter(
    private val project: Project,
) : GradleGenerationPipelineStarter {
    private val context: PipelineContext = BasicPipelineContext()
    private val parser: BasicYamlParser = BasicYamlParser()

    override fun runPipeline(context: TaskContext) {
        fillContextStartParams(context)
        val accessor = FileLoaderAccessor(listOf(ConfigurationFileLoader(project), RootFileLoader))
        val codeGenerator = BasicGenerator(context)
        val docsGenerator = DocumentationGenerator(this@GradleKotlinGenerationPipelineStarter.context)
        val pipeline = BasicGenerationPipeline(GenerationFlowDefinition(context))

        pipeline.generate {
            process(LoadPipe(accessor)) {
                process(ParsePipe(parser), it) {
                    if (context.generateModel) process(GeneratePipe(codeGenerator), it)
                    if (context.generateModelDocs) process(GeneratePipe(docsGenerator), it)
                }
            }
        }
    }

    private fun fillContextStartParams(context: TaskContext) {
        context.generatorFileExtension = "kt"
        context.sourceDir = "src/main/kotlin"
    }
}
