package io.github.alexsarrell.arch.core.generator.helper

import io.github.alexsarrell.arch.core.util.Jackson
import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

class JsonFormatHelper : Helper<Any?> {
    override fun apply(context: Any?, options: Options): Any? {
        return Jackson.objectMapper.writeValueAsString(context)
    }
}