package io.github.alexsarrell.arch.gradle.api.pipeline.pipe

import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.pipeline.context.importMappingsFile
import io.github.alexsarrell.arch.core.pipeline.context.loaderIgnoreResource
import io.github.alexsarrell.arch.core.pipeline.pipe.StartPipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.specFiles
import io.github.alexsarrell.arch.core.pipeline.pipe.context.typeMappingContent
import io.github.alexsarrell.arch.gradle.api.io.FileLoaderAccessor
import io.github.alexsarrell.arch.gradle.api.util.loadResourceLines

class LoadPipe(
    private val accessor: FileLoaderAccessor,
) : StartPipe<LoadPipeContext>() {
    override fun process(context: TaskContext): LoadPipeContext {
        val pipeContext = LoadPipeContext(context)

        val loaderIgnore =
            loadResourceLines(context.loaderIgnoreResource)
                .plus(context.loaderIgnore)
                .map { it.toRegex() }

        pipeContext.specFiles =
            accessor
                .getLoader(context.specSource)
                .get(context.specSource)
                .filter { file ->
                    !loaderIgnore.any { it.containsMatchIn(file.name) }
                }.toSet()

        pipeContext.typeMappingContent = getMappings(context.importMappingsFile)

        return pipeContext
    }

    private fun getMappings(resource: String): String? {
        val mappings = this::class.java.classLoader.getResourceAsStream(resource)?.reader()
            ?.use { it.readText() }
        return mappings
    }
}
