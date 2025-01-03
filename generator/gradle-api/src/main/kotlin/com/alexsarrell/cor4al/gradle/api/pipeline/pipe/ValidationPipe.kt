package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.AbstractPipe
import groovy.test.NotYetImplemented

class ValidationPipe(context: Cor4alContext) : AbstractPipe<ValidationPipe>(context) {
    @NotYetImplemented
    override fun process() {
        throw NotImplementedError()
    }
}
