package com.alexsarrell.cor4al.generator.gradle.io

import org.gradle.api.Project
import java.io.File

interface SpecLoader {

    fun get(specDir: String, project: Project): Set<File>
}
