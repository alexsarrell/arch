package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext
import com.alexsarrell.cor4al.core.pipeline.helper.GenerationFlowDefinition

interface GenerationPipeline {
    val context: PipelineContext
    fun generate(flow: GenerationFlowDefinition.() -> Unit)
}


