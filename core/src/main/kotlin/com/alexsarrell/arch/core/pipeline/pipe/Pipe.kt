package com.alexsarrell.arch.core.pipeline.pipe

import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

interface Pipe {
    val pipeContext: PipeContext?
        get() = null
}
