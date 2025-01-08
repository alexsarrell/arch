package com.alexsarrell.arch.core.pipeline.pipe

import com.alexsarrell.arch.core.pipeline.context.PipelineContext

abstract class StandalonePipe : Pipe {

    abstract fun PipelineContext.process()
}
