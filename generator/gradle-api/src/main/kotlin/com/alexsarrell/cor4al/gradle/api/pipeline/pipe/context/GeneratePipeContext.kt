package com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext

class GeneratePipeContext(context: Cor4alContext) : PipeContext(context) {
    var packageName: String = ""
}