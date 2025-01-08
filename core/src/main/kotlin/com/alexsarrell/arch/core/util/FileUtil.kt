package com.alexsarrell.arch.core.util

import com.github.jknack.handlebars.Template
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.Charset

fun File.relativeToBaseDir(baseDir: String): String {
    return this.relativeTo(File(baseDir)).path
}

fun File.relativeToBaseFile(baseFile: File): String {
    return this.relativeTo(baseFile).path
}

fun File.build() = apply { parentFile?.mkdirs() }

fun Template.writeToFile(context: Any, file: File, charset: Charset = Charsets.UTF_8) {
    FileOutputStream(file).use { fos ->
        OutputStreamWriter(fos, charset).use { writer ->
            apply(context, writer)
        }
    }
}