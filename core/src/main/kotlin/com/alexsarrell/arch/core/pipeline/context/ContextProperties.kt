package com.alexsarrell.arch.core.pipeline.context

var PipelineContext.outputDir: String by ContextProperty("outputDir")

var PipelineContext.packageName: String by ContextProperty("packageName")

var PipelineContext.templateDir: String? by ContextProperty("templateDir")

var PipelineContext.importMappings: Map<String, String> by ContextProperty("typeMappings")

var PipelineContext.specSource: String by ContextProperty("specSource")

var PipelineContext.specLimit: List<String> by ContextProperty("specLimit")

var PipelineContext.generatorFileExtension: String by ContextProperty("generatorFileExtension")

var PipelineContext.metadataAccessors: Boolean by ContextProperty("metadataAccessors")

var PipelineContext.generateModel: Boolean by ContextProperty("generateModel")

var PipelineContext.generateModelDocs: Boolean by ContextProperty("generateModelDocs")

var PipelineContext.modelDocsOutputDir: String by ContextProperty("modelDocsOutputDir")

var PipelineContext.modelDocType: String by ContextProperty("modelDocType")