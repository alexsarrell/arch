package io.github.alexsarrell.arch.gradle.api.util

import io.github.alexsarrell.arch.core.model.DocFormat
import io.github.alexsarrell.arch.core.model.DocLocale
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask

fun TaskContext.Companion.build(task: ArchGenerateTask): TaskContext {
    return TaskContext(
        task.specSource.get(),
        task.specsLimit.orNull ?: listOf(),
        task.importMappings.get(),
        task.metadataAccessors.getOrElse(true),
        task.loaderIgnore.getOrElse(listOf()),
        task.outputDir.get(),
        task.templateDir.orNull,
        task.packageName.get(),
        task.generateModelDocs.getOrElse(false),
        task.generateModel.getOrElse(true),
        task.modelDocsOutputDir.orNull ?: "docs",
        task.modelDocsFormat.orNull?.let { DocFormat.valueOf(it) } ?: DocFormat.MARKDOWN,
        task.modelDocsLocale.orNull?.let { DocLocale.valueOf(it) } ?: DocLocale.EN,
    )
}
