package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.SpecParser
import com.alexsarrell.cor4al.core.pipeline.pipe.Pipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask

class ParsePipe(val parser: SpecParser, val task: Cor4alGenerateTask): Pipe {
    override val pipeContext: PipeContext = parser.context()

    override fun process() {
        val loadContext = pipeContext.context.getContext(LoadPipeContext::class.java)
        parser.loadSpecs(loadContext.specFiles, task.specsLimit.get())
    }
}
