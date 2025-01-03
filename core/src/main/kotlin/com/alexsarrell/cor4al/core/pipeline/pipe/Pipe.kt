package com.alexsarrell.cor4al.core.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

interface Pipe {
    val pipeContext: PipeContext

    fun process()
}