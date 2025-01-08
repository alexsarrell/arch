package com.alexsarrell.arch.core.pipeline.pipe

import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

interface Pipe {

    fun pipeContext(): PipeContext? = null
}
