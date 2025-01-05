package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.context.BasicContext
import com.alexsarrell.cor4al.core.pipeline.context.GenerationContext
import com.alexsarrell.cor4al.core.pipeline.helper.GenerationFlowDefinition

class BasicGenerationPipeline : GenerationPipeline {
    override val context: GenerationContext = BasicContext()
    private val flowDefinition: GenerationFlowDefinition = GenerationFlowDefinition(context)

    override fun generate(flow: GenerationFlowDefinition.() -> Unit) {
        flow(flowDefinition)
    }
}