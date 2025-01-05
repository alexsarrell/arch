package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.context.GenerationContext
import com.alexsarrell.cor4al.core.pipeline.helper.GenerationFlowDefinition

interface GenerationPipeline {
    val context: GenerationContext
    fun generate(flow: GenerationFlowDefinition.() -> Unit)
}


