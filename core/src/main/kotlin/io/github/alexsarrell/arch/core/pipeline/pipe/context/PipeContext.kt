package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.context.ContextHolder

open class PipeContext(taskContext: TaskContext) : ContextHolder by taskContext