package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.context.ContextHolder

open class PipeContext(val taskContext: TaskContext) : ContextHolder by taskContext