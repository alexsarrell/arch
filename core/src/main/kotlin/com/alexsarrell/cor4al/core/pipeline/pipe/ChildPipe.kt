package com.alexsarrell.cor4al.core.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

abstract class ChildPipe<C : PipeContext>(val parentContext: Class<C>) : Pipe {

    abstract fun process(parentContext: C)
}