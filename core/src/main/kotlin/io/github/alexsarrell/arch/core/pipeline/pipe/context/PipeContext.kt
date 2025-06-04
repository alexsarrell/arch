package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.pipeline.context.BasicContextHolder
import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext

open class PipeContext(val pipelineContext: PipelineContext) : BasicContextHolder()