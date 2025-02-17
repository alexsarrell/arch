package com.alexsarrell.arch.core.generator.helper

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

class IsEmptyHelper : Helper<Collection<*>> {
    override fun apply(context: Collection<*>, options: Options?): Any {
        return context.isEmpty()
    }
}
