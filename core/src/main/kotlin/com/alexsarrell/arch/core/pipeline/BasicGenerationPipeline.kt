package com.alexsarrell.arch.core.pipeline

import com.alexsarrell.arch.core.pipeline.context.BasicPipelineContext
import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.helper.GenerationFlowDefinition

class BasicGenerationPipeline : GenerationPipeline {
    override val context: PipelineContext = BasicPipelineContext()
    private val flowDefinition: GenerationFlowDefinition = GenerationFlowDefinition(context)

    override fun generate(flow: GenerationFlowDefinition.() -> Unit) {
        flow(flowDefinition)
    }
}