package com.alexsarrell.cor4al.gradle.api

import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask

interface GradleGenerationPipelineStarter {

    fun runPipeline(task: Cor4alGenerateTask)
}
