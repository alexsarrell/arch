package com.alexsarrell.arch.gradle.api

import com.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask

interface GradleGenerationPipelineStarter {

    fun runPipeline(task: ArchGenerateTask)
}
