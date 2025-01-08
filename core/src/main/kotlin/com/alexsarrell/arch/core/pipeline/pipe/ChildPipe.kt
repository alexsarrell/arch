package com.alexsarrell.arch.core.pipeline.pipe

import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

abstract class ChildPipe<C : PipeContext>(val parentContext: Class<C>) : Pipe {

    abstract fun PipelineContext.process(parentContext: C)
}