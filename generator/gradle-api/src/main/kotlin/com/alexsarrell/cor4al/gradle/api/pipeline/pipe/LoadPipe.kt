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
        pipeContext.specFiles = accessor.getLoader(specSource).get(specSource, task.project)
    }

    private fun loadTemplates(resourcePath: String): Map<String, String> {
        val classLoader = Thread.currentThread().contextClassLoader
        val resource = classLoader.getResource(resourcePath)
        val templates = mutableMapOf<String, String>()
        if (resource != null) {
            val uri = resource.toURI()
            val jarPath = uri.schemeSpecificPart.substringBefore("!")

            val fileSystem = try {
                FileSystems.getFileSystem(URI.create("jar:$jarPath"))
            } catch (e: java.nio.file.FileSystemNotFoundException) {
                FileSystems.newFileSystem(URI.create("jar:$jarPath"), emptyMap<String, Any>())
            }

            val path = fileSystem.getPath(resourcePath)
            Files.walk(path, 1).use { paths ->
                paths.filter { Files.isRegularFile(it) && (it.toString().endsWith(".mustache") || it.toString().endsWith(".hbs")) }
                    .forEach { filePath ->
                        val fileName = filePath.fileName.toString()
                        val inputStream = classLoader.getResourceAsStream("$resourcePath/$fileName")

                        requireNotNull(inputStream) { "Failed to load template file as input stream $fileName" }

                        val content = InputStreamReader(inputStream).readText()

                        templates[fileName.split(".")[0]] = content
                    }
            }
        }
        return templates
    }
}
