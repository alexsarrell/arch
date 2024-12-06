package com.alexsarrell.cor4al.generator.gradle.tasks

import com.alexsarrell.cor4al.core.AbstractYamlParser
import com.alexsarrell.cor4al.generator.gradle.io.SpecLoader
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class Cor4alGenerateTask : DefaultTask() {

    @get:Input
    abstract val specDir: Property<String>

    @get:Input
    abstract val specLoader: Property<SpecLoader>

    @get:Input
    abstract val parser: Property<AbstractYamlParser>

    @get:OutputDirectory
    abstract val outputDir: Property<String>

    @get:Input
    abstract val packageName: Property<String>

    init {
        group = "generation"
        description = "Generates model from specification"
    }

    @TaskAction
    fun generate() {
        val specFiles = specLoader.get().get(specDir.get(), project)
        val targetDir = File(outputDir.get())

        parser.get().validate()
        parser.get().loadFiles(specFiles.toList())

        targetDir.mkdirs()
    }
}

// TODO Протестировать и отладить загрузку файлов
// TODO Написать шаблоны и требования для генератора
// TODO Написать генератор классы