package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.Cor4alGenerator
import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.AbstractPipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.GeneratePipeContext
import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask

class GeneratePipe(
    context: Cor4alContext,
    private val cor4alGenerator: Cor4alGenerator,
    private val task: Cor4alGenerateTask,
) : AbstractPipe<GeneratePipe>(context) {
    override val pipeContext: PipeContext = GeneratePipeContext(context)

    override fun process() {
        pipeContext as GeneratePipeContext
        pipeContext.packageName = task.packageName.get()

        cor4alGenerator.generate(pipeContext.context)
    }
}