package com.alexsarrell.arch.gradle.api

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import javax.inject.Inject

open class ArchExtension @Inject constructor(
    objects: ObjectFactory
) {
    @Input
    val pipelineStarter: Property<GradleGenerationPipelineStarter> =
        objects.property(GradleGenerationPipelineStarter::class.java)
}
