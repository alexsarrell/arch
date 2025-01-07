package com.alexsarrell.arch.gradle.api.io

class FileLoaderAccessor(private val loaders: List<FileLoader>) {

    fun getLoader(filePath: String): FileLoader {
        return loaders.first { it.canLoad(filePath) }
    }
}
