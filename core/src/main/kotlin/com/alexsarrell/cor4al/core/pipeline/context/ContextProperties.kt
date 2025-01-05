package com.alexsarrell.cor4al.core.pipeline.context

var PipelineContext.outputDir: String by ContextProperty("outputDir")

var PipelineContext.packageName: String by ContextProperty("packageName")

var PipelineContext.parentPackage: String by ContextProperty("parentPackage")

var PipelineContext.templateDir: String? by ContextProperty("templateDir")

var PipelineContext.typeMappings: Map<String, String> by ContextProperty("typeMappings")

var PipelineContext.specSource: String by ContextProperty("specSource")

var PipelineContext.specLimit: List<String> by ContextProperty("specLimit")

var PipelineContext.generatorFileExtension: String by ContextProperty("generatorFileExtension")
