package io.github.alexsarrell.arch.core.pipeline.pipe

import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

abstract class ChildPipe<C : PipeContext>(val parentContext: Class<C>) : Pipe {

    abstract fun PipelineContext.process(parentContext: C)
}