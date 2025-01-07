package com.alexsarrell.arch.core.pipeline.pipe.context

import com.alexsarrell.arch.core.pipeline.context.BasicContextHolder
import com.alexsarrell.arch.core.pipeline.context.PipelineContext

open class PipeContext(val pipelineContext: PipelineContext) : BasicContextHolder()