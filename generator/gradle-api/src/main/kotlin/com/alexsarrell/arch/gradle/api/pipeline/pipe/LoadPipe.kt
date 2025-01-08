package com.alexsarrell.arch.gradle.api.pipeline.pipe

import com.alexsarrell.arch.core.pipeline.context.PipelineContext
import com.alexsarrell.arch.core.pipeline.pipe.StandalonePipe
import com.alexsarrell.arch.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.arch.gradle.api.io.ConfigurationFileLoader
import com.alexsarrell.arch.gradle.api.io.FileLoaderAccessor
import com.alexsarrell.arch.gradle.api.io.RootFileLoader
import com.alexsarrell.arch.core.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.arch.core.pipeline.pipe.context.specFiles
import com.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask
import com.alexsarrell.arch.gradle.api.util.loadResourceLines

class LoadPipe(
    private val task: ArchGenerateTask,
) : StandalonePipe() {
    private lateinit var pipeContext: LoadPipeContext

    override fun pipeContext(): PipeContext {
        return pipeContext
    }

    private val accessor = FileLoaderAccessor(
        listOf(ConfigurationFileLoader, RootFileLoader)
    )

    override fun PipelineContext.process() {
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
