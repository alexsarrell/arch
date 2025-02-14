package com.alexsarrell.arch.gradle.api.tasks

import com.alexsarrell.arch.gradle.api.GradleGenerationPipelineStarter
import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import javax.annotation.Nullable

abstract class ArchGenerateTask : DefaultTask() {

    @get:Input
    abstract val specSource: Property<String>

    @get:Input
    @get:Optional
    abstract val specsLimit: ListProperty<String>

    @get:Input
    @get:Optional
    abstract val importMappings: MapProperty<String, String>

    @get:Input
    @get:Optional
    abstract val metadataAccessors: Property<Boolean>

    @get:Input
    @get:Optional
    abstract val loaderIgnore: ListProperty<String>

    @get:OutputDirectory
    @get:Optional
    abstract val outputDir: Property<String>

    @get:InputDirectory
    @get:Optional
    @get:Nullable
    abstract val templateDir: Property<String>

    @get:Input
    abstract val packageName: Property<String>

    @get:Input
    @get:Optional
    abstract val generateModelDocs: Property<Boolean>

    @get:Input
    @get:Optional
    abstract val generateModel: Property<Boolean>

    @get:InputDirectory
    @get:Optional
    abstract val modelDocsOutputDir: Property<String>

    @get:Input
    @get:Optional
    abstract val modelDocsFormat: ListProperty<String>

    @get:Input
    @get:Optional
    abstract val modelDocsLocale: Property<String>

    @get:Internal
    abstract val pipelineStarter: Property<GradleGenerationPipelineStarter>

    init {
        group = "generation"
        description = "Generates model from specification"
        doNotTrackState("If you want to always regenerate model classes")
    }

    final override fun doNotTrackState(reasonNotToTrackState: String) {
        super.doNotTrackState(reasonNotToTrackState)
    }

    @TaskAction
    fun generate() {
        pipelineStarter.get().runPipeline(this)
    }
}
