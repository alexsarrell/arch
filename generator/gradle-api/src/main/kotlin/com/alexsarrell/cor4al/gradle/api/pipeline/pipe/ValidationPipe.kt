package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.pipe.StandalonePipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import groovy.test.NotYetImplemented

class ValidationPipe : StandalonePipe() {
    override val pipeContext: PipeContext
        get() = TODO("Not yet implemented")

    @NotYetImplemented
    override fun process() {
        throw NotImplementedError()
    }
}
