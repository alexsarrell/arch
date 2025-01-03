package com.alexsarrell.cor4al.gradle.api.tasks

import com.alexsarrell.cor4al.gradle.api.GradleGenerationPipelineStarter
import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class Cor4alGenerateTask : DefaultTask() {

    @get:Input
    abstract val specSource: Property<String>

    @get:Input
    @get:Optional
    abstract val specsLimit: ListProperty<String>

    @get:Input
    @get:Optional
    abstract val parser: Property<String>

    @get:OutputDirectory
    @get:Optional
    abstract val outputDir: Property<String>

    @get:Input
    @get:Optional
    abstract val packageName: Property<String>

    @get:Input
    abstract val mode: Property<String>

    @get:Internal
    abstract val pipelineStarter: Property<GradleGenerationPipelineStarter>

    init {
        group = "generation"
        description = "Generates model from specification"
    }

    @TaskAction
    fun generate() {
        pipelineStarter.get().runPipeline(this)
    }
}

// TODO Протестировать и отладить загрузку файлов
// TODO Написать шаблоны и требования для генератора
// TODO Написать генератор классы