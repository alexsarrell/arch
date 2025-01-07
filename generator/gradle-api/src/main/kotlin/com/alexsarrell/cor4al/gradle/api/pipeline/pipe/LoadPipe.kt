package com.alexsarrell.cor4al.gradle.api.pipeline.pipe

import com.alexsarrell.cor4al.core.pipeline.context.PipelineContext
import com.alexsarrell.cor4al.core.pipeline.pipe.StandalonePipe
import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import com.alexsarrell.cor4al.gradle.api.io.ConfigurationFileLoader
import com.alexsarrell.cor4al.gradle.api.io.FileLoaderAccessor
import com.alexsarrell.cor4al.gradle.api.io.RootFileLoader
import com.alexsarrell.cor4al.core.pipeline.pipe.context.LoadPipeContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.specFiles
import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask
import com.alexsarrell.cor4al.gradle.api.util.loadResourceLines
import java.io.InputStreamReader
import java.net.URI
import java.nio.file.FileSystems
import java.nio.file.Files

class LoadPipe(
    context: PipelineContext,
    private val task: Cor4alGenerateTask,
) : StandalonePipe() {
    override val pipeContext: PipeContext = LoadPipeContext(context)

    private val accessor = FileLoaderAccessor(
        listOf(ConfigurationFileLoader, RootFileLoader)
    )

    override fun process() {
        pipeContext as LoadPipeContext
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
