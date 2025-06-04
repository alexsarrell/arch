package io.github.alexsarrell.arch.gradle.api

import io.github.alexsarrell.arch.core.model.TaskContext

interface GradleGenerationPipelineStarter {

    fun runPipeline(context: TaskContext)
}
