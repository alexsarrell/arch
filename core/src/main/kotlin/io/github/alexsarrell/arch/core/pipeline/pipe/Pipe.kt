package io.github.alexsarrell.arch.core.pipeline.pipe

import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.context.ContextHolder
import io.github.alexsarrell.arch.core.pipeline.pipe.context.PipeContext

interface Pipe

interface ReturningPipe<Input : ContextHolder, Result : PipeContext> : Pipe {
    fun process(context: Input): Result
}

abstract class StartPipe<Result : PipeContext> : ReturningPipe<TaskContext, Result>

abstract class AdjacentPipe<Input : PipeContext, Result : PipeContext>() : ReturningPipe<Input, Result>

abstract class FinalPipe<Parent : PipeContext>() : Pipe {
    abstract fun process(context: Parent)
}
