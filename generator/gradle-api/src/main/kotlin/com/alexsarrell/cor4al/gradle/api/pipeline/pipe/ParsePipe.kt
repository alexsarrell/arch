package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.parser.SpecParser
import com.alexsarrell.cor4al.core.pipeline.pipe.ChildPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask

class ParsePipe(
    private val parser: SpecParser,
    private val task: Cor4alGenerateTask,
) : ChildPipe<LoadPipeContext>(LoadPipeContext::class.java) {
    override val pipeContext: PipeContext = parser.context()

    override fun process(parentContext: LoadPipeContext) {
        parser.loadSpecs(parentContext.specFiles, task.specsLimit.get())
    }
}
