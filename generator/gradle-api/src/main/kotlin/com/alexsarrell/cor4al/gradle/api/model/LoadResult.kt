package com.alexsarrell.cor4al.gradle.api.model

import java.io.File

data class LoadResult(
    val baseDir: File,
    val specFiles: Set<File>,
)