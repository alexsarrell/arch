package com.alexsarrell.arch.core.generator.helper

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

class PadRightHelper : Helper<Any> {
    override fun apply(context: Any?, options: Options): CharSequence {
        val length = options.param(0, context?.toString()?.length ?: 0)
        return String.format("%-${length}s", context)
    }
}