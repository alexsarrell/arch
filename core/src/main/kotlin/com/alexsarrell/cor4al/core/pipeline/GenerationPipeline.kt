package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.helper.GenerationFlowDefinition

interface GenerationPipeline {
    val context: Cor4alContext
    fun generate(flow: GenerationFlowDefinition.() -> Unit)
}


