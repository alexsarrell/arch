package com.alexsarrell.cor4al.gradle.api

import com.alexsarrell.cor4al.core.Cor4alGenerator
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import javax.inject.Inject

open class Cor4alExtension @Inject constructor(
    objects: ObjectFactory
) {
    @Input
    val templateDir: Property<String> =
        objects.property(String::class.java).convention("src/main/conf/templates")

    @Input
    val generator: Property<Cor4alGenerator> =
        objects.property(Cor4alGenerator::class.java)

    @Input
    val pipelineStarter: Property<GradleGenerationPipelineStarter> =
        objects.property(GradleGenerationPipelineStarter::class.java)
}
