package com.alexsarrell.cor4al.core.pipeline

import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

interface Cor4alContext {

    fun contexts(): Map<String, PipeContext>

    fun <P: Pipe> register(pipe: P)

    fun <P : PipeContext> getContext(pipeClass: Class<P>): P
}
