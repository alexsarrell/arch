package com.alexsarrell.arch.core.pipeline.context

import com.alexsarrell.arch.core.model.DocType

var PipelineContext.outputDir: String by ContextProperty("outputDir")

var PipelineContext.sourceDir: String by ContextProperty("sourceDir")

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

var PipelineContext.modelDocType: DocType by ContextProperty("modelDocType")