package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.helper.GenerationFlowDefinition

class BasicGenerationPipeline : GenerationPipeline {
    override val context: Cor4alContext = BasicContext()
    private val flowDefinition: GenerationFlowDefinition = GenerationFlowDefinition(context)

    override fun generate(flow: GenerationFlowDefinition.() -> Unit) {
        flow(flowDefinition)
    }
}