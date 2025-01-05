package com.alexsarrell.cor4al.core.parser

import com.alexsarrell.cor4al.core.pipeline.pipe.context.PipeContext
import java.io.File

interface SpecParser {

    fun context(): PipeContext

    fun loadSpecs(specs: Set<File>, specsLimit: List<String>)
}
