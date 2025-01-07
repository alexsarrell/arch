package com.alexsarrell.arch.core.pipeline.context

import com.alexsarrell.arch.core.pipeline.pipe.Pipe
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

interface PipelineContext : ContextHolder {
    fun <P: Pipe> register(pipe: P)

    fun <C : PipeContext> getContext(contextClass: Class<C>): C
}
