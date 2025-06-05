package io.github.alexsarrell.arch.core.pipeline.context

import io.github.alexsarrell.arch.core.model.TaskContext

var TaskContext.sourceDir: String by ContextProperty("sourceDir")

var TaskContext.generatorFileExtension: String by ContextProperty("generatorFileExtension")

var TaskContext.importMappingsFile: String by ContextProperty("importMappingsFile")

var TaskContext.loaderIgnoreResource: String by ContextProperty("loaderIgnoreResource")
