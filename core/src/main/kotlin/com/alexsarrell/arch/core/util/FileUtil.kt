package com.alexsarrell.arch.core.util

import java.io.File

fun File.relativeToBaseDir(baseDir: String): String {
    return this.relativeTo(File(baseDir)).path
}

fun File.relativeToBaseFile(baseFile: File): String {
    return this.relativeTo(baseFile).path
}

fun File.createAndWriteText(text: String) = apply { parentFile?.mkdirs() }.writeText(text)