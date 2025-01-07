# Automated Rendering of Code Hierarchies Library

ARCH is a Kotlin-based code generation library with flexible configuration for generating analytics event models using a simple YAML-based specification. The library supports the generation of classes with hierarchical inheritance, abstract classes, and data class models.

The primary application of this library is the generation of models for analytics in projects with strict requirements for versioning and standardization. The library provides capabilities for tracking model versions and properties, as well as descriptions for properties and models. In the future, automatic generation of changelog files and .MD documentation for each specification will be added. The core concept of the library is the implementation of a common abstract analytics layer for interaction between data analysts, project managers, and developers, focusing on standardization and documentation.

To use in a project, you need to add the JitPack.io repository, apply the following plugin:

```kotlin
plugins {
    id("com.alexsarrell.arch.generator.kotlin.plugin") version "1.0.0"
}

repositories {
    maven("https://jitpack.io")
}
```

add the following dependency:

```kotlin
dependencies {
    api("com.alexsarrell.arch-generator:gradle-kotlin-plugin:1.0.0")
}
```

and register the Gradle task ArchGenerateTask:
```kotlin

tasks.register<ArchGenerateTask>("archGenerate") {
    specSource.set(project.file(".").absolutePath)
    outputDir.set("${project.layout.buildDirectory.get()}/generated")
    packageName.set("com.alexsarrell.demo")
}
```

| Property          | Description                                                                                        | Example                                                           | Required | Default |
|-------------------|----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|----------|---------|
| specSource        | Source of specification files. Library provides support for both direct path and dependency links. | /etc/alexsarrell/demo/spec<br/>com.alexsarrell.example:/demo/spec | true     |         |
| outputDir         | The directory to generate models.                                                                  |                                                                   | true     |         |
| packageName       | The package will be used for models.                                                               |                                                                   | true     |         |
| specsLimit        | List of file paths (relative to specSource) to use for generation (to exclude another ones).       | listOf("/data/core-demo.yml")                                     | false    | []      |
| importMappings    | Additional mappings for import. Applies for all defined models.                                    | mapOf("Clock" to "java.time.Clock")                               | false    | []      |
| metadataAccessors | Generate getters for metadata values or not                                                        |                                                                   | false    | true    |
| loaderIgnore      | List of regular expressions defining ignore rules for specification loader                         | /**/pom.xml<br/>module.json                                       | false    |         |
| templateDir       | Path to directory with templates for base templates override                                       |                                                                   | false    |         |

Examples of task configuration and specifications for generation are provided in the :demo module.