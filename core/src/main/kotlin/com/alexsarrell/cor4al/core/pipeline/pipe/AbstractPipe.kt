package com.alexsarrell.cor4al.core.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

abstract class AbstractPipe<P : Pipe>(
    context: Cor4alContext,
) : Pipe {
    override val pipeContext: PipeContext = PipeContext(context)
}