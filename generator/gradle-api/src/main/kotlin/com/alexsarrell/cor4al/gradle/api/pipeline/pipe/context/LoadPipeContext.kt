package com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import java.io.File

class LoadPipeContext(context: Cor4alContext): PipeContext(context) {
    var specFiles: Set<File> = setOf()
    var templates: Map<String, String> = mapOf()
}
