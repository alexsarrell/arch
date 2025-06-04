package io.github.alexsarrell.arch.gradle.plugin

import io.github.alexsarrell.arch.gradle.api.ArchExtension
import io.github.alexsarrell.arch.gradle.api.plugin.ArchGeneratorPlugin
import io.github.alexsarrell.arch.gradle.api.tasks.ArchGenerateTask
import io.github.alexsarrell.arch.gradle.generator.GradleKotlinGenerationPipelineStarter
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinPluginModule : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(ArchGeneratorPlugin::class.java)

        project.afterEvaluate {
            project.extensions.configure(ArchExtension::class.java) {
                pipelineStarter.set(GradleKotlinGenerationPipelineStarter())
            }

            project.tasks.withType(ArchGenerateTask::class.java).configureEach {
                val extension = project.extensions.getByType(ArchExtension::class.java)
                pipelineStarter.set(extension.pipelineStarter)
            }
        }
    }
}
