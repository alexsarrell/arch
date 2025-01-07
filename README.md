# cor4al-generator

Cor4alGenerator is a Kotlin-based code generation library with flexible configuration for generating analytics event models using a simple YAML-based specification. The library supports the generation of classes with hierarchical inheritance, abstract classes, and data class models. In the future, there are plans to add support for JSON-based specifications and the ability to validate the specification before generation.

To use in a project, you need to apply the following plugin:

```kotlin
plugins {
    id("com.alexsarrell.cor4al.generator.kotlin.plugin") version "1.0.0"
}
```

add the following dependency:

```kotlin
dependencies {
    api("com.alexsarrell.cor4al-generator:gradle-kotlin-plugin:1.0.0")
}
```

and register the Gradle task Cor4alGenerateTask:
```kotlin

tasks.register<Cor4alGenerateTask>("cor4alGenerate") {
    specSource.set(project.file(".").absolutePath)
    outputDir.set("${project.layout.buildDirectory.get()}/generated")
    packageName.set("com.alexsarrell.demo")
}
```

| Property   | Description                                                                                        | Example                                                           |
|------------|----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|
| specSource | Source of specification files. Library provides support for both direct path and dependency links. | /etc/alexsarrell/demo/spec<br/>com.alexsarrell.example:/demo/spec |

Examples of task configuration and specifications for generation are provided in the :demo module.