package com.alexsarrell.arch.core.pipeline

import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.helper.GenerationFlowDefinition

interface GenerationPipeline {
    val context: PipelineContext

    fun generate(flow: GenerationFlowDefinition.() -> Unit)
}


