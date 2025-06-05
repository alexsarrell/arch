package io.github.alexsarrell.arch.core.pipeline.pipe.context

import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.context.ContextProperty
import java.io.File

class LoadPipeContext(context: TaskContext): PipeContext(context)

var LoadPipeContext.specFiles: Set<File> by ContextProperty("specFiles")

var LoadPipeContext.typeMappingContent: String? by ContextProperty("typeMappingContent")
