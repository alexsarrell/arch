package com.alexsarrell.cor4al.core.pipeline.pipe.context

import com.alexsarrell.cor4al.core.pipeline.context.BasicContextHolder
import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext

open class PipeContext(val pipelineContext: PipelineContext) : BasicContextHolder()