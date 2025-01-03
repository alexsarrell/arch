package com.alexsarrell.cor4al.gradle.generator

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.BasicGenerationPipeline
import com.alexsarrell.cor4al.core.pipeline.GenerationPipeline
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.GeneratePipe
import com.alexsarrell.cor4al.generator.kotlin.parser.KotlinYamlParser
import com.alexsarrell.cor4al.gradle.api.GradleGenerationPipelineStarter
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.ParsePipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.cor4al.generator.kotlin.Cor4alKotlinGenerator
import com.alexsarrell.cor4al.gradle.api.GenerationMode
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.LoadPipe
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.ValidationPipe
import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask

class GradleKotlinGenerationPipelineStarter : GradleGenerationPipelineStarter {
    private val pipeline: GenerationPipeline = BasicGenerationPipeline()
    private val context: Cor4alContext = pipeline.context
    private val parser: KotlinYamlParser = KotlinYamlParser(ParsePipeContext(context))
    private val generator: Cor4alKotlinGenerator = Cor4alKotlinGenerator()

    override fun runPipeline(task: Cor4alGenerateTask) {
        pipeline.generate {
            process(LoadPipe(context, task))
            process(ParsePipe(parser, task))
            process(ValidationPipe(context)) { task.mode.get().equals(GenerationMode.SAFE.name, ignoreCase = true) }
            process(GeneratePipe(context, generator, task))
        }
    }
}
