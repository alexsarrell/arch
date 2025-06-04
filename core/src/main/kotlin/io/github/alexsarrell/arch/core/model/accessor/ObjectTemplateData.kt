package io.github.alexsarrell.arch.core.model.accessor

import io.github.alexsarrell.arch.core.model.Schema
import io.github.alexsarrell.arch.core.model.TaskContext
import io.github.alexsarrell.arch.core.util.buildImports
import io.github.alexsarrell.arch.core.util.extractParentFields
import io.github.alexsarrell.arch.core.util.isOpen
import io.github.alexsarrell.arch.core.util.toMetadataEntry
import io.github.alexsarrell.arch.core.util.toModelProperties

data class ObjectTemplateData(
    val className: String,
    val description: String?,
    val parent: String?,
    val parentProperties: List<ObjectProperty>,
    val version: String,
    val packageName: String,
    val properties: List<ObjectProperty>,
    val isOpen: Boolean,
    val enum: List<String>,
    val imports: List<String>?,
    val metadata: List<MetadataEntry>?,
    val metadataAccessors: Boolean,
) {
    data class MetadataEntry(
        val key: String,
        val value: String,
    )

    data class ObjectProperty(
        val name: String,
        val required: Boolean,
        val description: String?,
        val type: String,
        val version: String,
        val defaultValue: String? = null,
    )

    constructor(className: String, classData: Schema, context: TaskContext) :
        this(
            className = className,
            parent = classData.parent?.className,
            version = classData.version,
            properties = classData.properties.toModelProperties(),
            parentProperties = classData.extractParentFields().toModelProperties(),
            packageName = context.packageName,
            isOpen = classData.isOpen(),
            enum = classData.enum,
            imports = classData.buildImports(context).distinct(),
            metadata = classData.metadata?.toMetadataEntry(),
            description = classData.description,
            metadataAccessors = context.metadataAccessors,
        )
}
