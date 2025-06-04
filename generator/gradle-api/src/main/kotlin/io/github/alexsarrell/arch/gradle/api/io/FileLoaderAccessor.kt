package io.github.alexsarrell.arch.gradle.api.io

import org.gradle.api.GradleException

class FileLoaderAccessor(private val loaders: List<FileLoader>) {

    fun getLoader(filePath: String): FileLoader {
        return loaders.find {
            it.canLoad(filePath)
        } ?: throw GradleException("File path '$filePath' cannot be resolved by any file loaders, please check it correct.")
    }
}
