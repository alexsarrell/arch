package com.alexsarrell.cor4al.core.pipeline.context

import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

interface PipelineContext : ContextHolder {
    fun <P: Pipe> register(pipe: P)

    fun <C : PipeContext> getContext(contextClass: Class<C>): C
}
