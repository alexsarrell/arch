package io.github.alexsarrell.arch.core.model

import io.github.alexsarrell.arch.core.pipeline.context.BasicContextHolder

data class TaskContext(
    val specSource: String,
    val specsLimit: List<String>,
    val importMappings: Map<String, String>,
    val metadataAccessors: Boolean,
    val loaderIgnore: List<String>,
    val outputDir: String,
    val templateDir: String?,
    val packageName: String,
    val generateModelDocs: Boolean,
    val generateModel: Boolean,
    val modelDocsOutputDir: String,
    val modelDocsFormat: DocFormat,
    val modelDocsLocale: DocLocale,
) : BasicContextHolder() {
    companion object
}
