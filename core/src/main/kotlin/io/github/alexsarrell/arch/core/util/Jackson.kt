package io.github.alexsarrell.arch.core.util

import com.fasterxml.jackson.databind.ObjectMapper

object Jackson {
    val objectMapper: ObjectMapper = getMapper()

    private fun getMapper() = ObjectMapper()
}
