package com.alexsarrell.cor4al.gradle.plugin

import com.alexsarrell.cor4al.gradle.api.Cor4alExtension
import com.alexsarrell.cor4al.gradle.api.plugin.Cor4alGeneratorPlugin
import com.alexsarrell.cor4al.gradle.api.tasks.Cor4alGenerateTask
import com.alexsarrell.cor4al.gradle.generator.GradleKotlinGenerationPipelineStarter
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinPluginModule : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(Cor4alGeneratorPlugin::class.java)

        project.afterEvaluate {
            project.plugins.withId("com.alexsarrell.cor4al.generator") {
                project.extensions.configure(Cor4alExtension::class.java) {
                    pipelineStarter.set(GradleKotlinGenerationPipelineStarter())
                }

                project.tasks.withType(Cor4alGenerateTask::class.java).configureEach {
                    val extension = project.extensions.getByType(Cor4alExtension::class.java)
                    pipelineStarter.set(extension.pipelineStarter)
                }
            }
        }
    }
}
