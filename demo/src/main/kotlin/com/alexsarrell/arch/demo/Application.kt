package io.github.alexsarrell.arch.demo

import com.alexsarrell.demo.ChildEventFileImport
import com.alexsarrell.demo.name
import java.util.UUID

fun main() {
    val event = ChildEventFileImport(sessionId = UUID.randomUUID())
    event.name // extracts name from metadata
}