package io.github.alexsarrell.arch.core.pipeline

import io.github.alexsarrell.arch.core.pipeline.helper.GenerationFlowDefinition

interface GenerationPipeline {
    fun generate(flow: GenerationFlowDefinition.() -> Unit)
}
