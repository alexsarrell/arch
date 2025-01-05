package com.alexsarrell.cor4al.core.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

interface Pipe {
    val pipeContext: PipeContext
        get() = throw IllegalStateException("No pipeline context declared for $this")
}
