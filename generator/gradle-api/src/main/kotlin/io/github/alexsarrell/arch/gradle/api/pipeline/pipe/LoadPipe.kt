package io.github.alexsarrell.arch.gradle.api.pipeline.pipe

import io.github.alexsarrell.arch.core.pipeline.context.PipelineContext
import io.github.alexsarrell.arch.core.pipeline.pipe.StandalonePipe
import io.github.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import io.github.alexsarrell.arch.gradle.api.io.ConfigurationFileLoader
import io.github.alexsarrell.arch.gradle.api.io.FileLoaderAccessor
import io.github.alexsarrell.arch.gradle.api.io.RootFileLoader
import io.github.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import io.github.alexsarrell.arch.core.pipeline.pipe.context.specFiles
import io.github.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask
import io.github.alexsarrell.arch.gradle.api.util.loadResourceLines

class LoadPipe: StandalonePipe() {
    private lateinit var pipeContext: LoadPipeContext

    override fun pipeContext(): PipeContext {
        return pipeContext
    }

    private val accessor = FileLoaderAccessor(
        listOf(ConfigurationFileLoader, RootFileLoader)
    )

    override fun process() {
        pipeContext = LoadPipeContext(this)

        val specSource = task.specSource.get()

        val loaderIgnore =
            loadResourceLines(LOADER_IGNORE_RESOURCE)
                .plus(task.loaderIgnore.getOrElse(listOf()))
                .map { it.toRegex() }

        pipeContext.specFiles =
            accessor.getLoader(specSource).get(specSource, task.project)
                .filter { file ->
                    !loaderIgnore.any { it.containsMatchIn(file.name) }
                }.toSet()
    }

    companion object {
        const val LOADER_IGNORE_RESOURCE = ".loaderignore"
    }
}
