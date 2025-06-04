package io.github.alexsarrell.arch.gradle.api.util

fun Any.loadResource(resourceName: String): String {
    return javaClass.classLoader.getResourceAsStream(resourceName)?.use { inputStream ->
        inputStream.bufferedReader().use { it.readText() }
    } ?: throw IllegalArgumentException("Resource $resourceName not found")
}

fun Any.loadResourceLines(resourceName: String): List<String> {
    return javaClass.classLoader.getResourceAsStream(resourceName)?.use { inputStream ->
        inputStream.bufferedReader().use { it.readLines() }
    } ?: throw IllegalArgumentException("Resource $resourceName not found")
}
