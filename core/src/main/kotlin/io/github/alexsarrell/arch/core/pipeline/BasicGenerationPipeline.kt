package io.github.alexsarrell.arch.core.pipeline

import io.github.alexsarrell.arch.core.pipeline.helper.GenerationFlowDefinition

class BasicGenerationPipeline(
    private val flowDefinition: GenerationFlowDefinition,
) : GenerationPipeline {

    override fun generate(flow: GenerationFlowDefinition.() -> Unit) {
        flow(flowDefinition)
    }
}