package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.pipeline.context.ContextProperty
import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext
import java.io.File

class LoadPipeContext(context: PipelineContext): PipeContext(context)

var LoadPipeContext.specFiles: Set<File> by ContextProperty("specFiles")
