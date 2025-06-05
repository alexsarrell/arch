package io.github.alexsarrell.arch.core.pipeline.helper

import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.pipe.AdjacentPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.FinalPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.StartPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

class GenerationFlowDefinition(
    private val context: TaskContext,
) {
    fun <Result : PipeContext> process(
        pipe: StartPipe<Result>,
        callback: GenerationFlowDefinition.(Result) -> Unit = {},
    ) {
        callback(pipe.process(context))
    }

    fun <Input : PipeContext, Result: PipeContext> process(
        pipe: AdjacentPipe<Input, Result>,
        context: Input,
        callback: GenerationFlowDefinition.(Result) -> Unit = {},
    ) {
        callback(pipe.process(context))
    }

    fun <Input : PipeContext> process(
        pipe: FinalPipe<Input>,
        context: Input,
    ) {
        pipe.process(context)
    }
}
