package io.github.alexsarrell.arch.core.pipeline.context

import io.github.alexsarrell.arch.core.model.TaskContext

var TaskContext.sourceDir: String by ContextProperty("sourceDir")

var TaskContext.specLimit: List<String> by ContextProperty("specLimit")

var TaskContext.generatorFileExtension: String by ContextProperty("generatorFileExtension")